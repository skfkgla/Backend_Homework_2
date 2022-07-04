package com.BackendHomework2.provider.service;


import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.core.service.common.CommonReviewService;
import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.ReviewEvent;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.exception.error.DuplicateReviewException;
import com.BackendHomework2.exception.error.NotFoundReviewException;
import com.BackendHomework2.exception.error.NotFoundUserException;
import com.BackendHomework2.repository.PhotoRepository;
import com.BackendHomework2.repository.ReviewEventRepository;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewEventsServiceImpl implements ReviewEventsService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final PhotoRepository photoRepository;
    private final ReviewEventRepository reviewEventRepository;
    private final CommonReviewService commonReviewService;
    /*
     Review action이 ADD일 경우
        1. 1자 이상의 리뷰 1점 추가
        2. 1장 이상의 사진 1점 추가
        3. 특정 장소에서 첫 리뷰라면 1점 추가(현재 시간 특정장소에 리뷰가 없으면)
        4. 리뷰 등록
     조건 - 한 장소에 리뷰를 남겼다면 다시 똑같은 장소에 리뷰를 남길 수 없음
    */
    @Override
    @Transactional
    public void addReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEvnetDto){
        User user = userRepository.findByUserId(reviewEvnetDto.getUserId());
        //예외 처리
        if(null == user){
            throw new NotFoundUserException();
        }
        if(null != reviewRepository.findByPlaceIdAndUserId(reviewEvnetDto.getPlaceId(), reviewEvnetDto.getUserId())){    // 조건
            throw new DuplicateReviewException();
        }

        int mileage = user.getMileage();

        if(reviewEvnetDto.getContent().length() > 0){                                    // 1.
            commonReviewService.registerReviewEvents(reviewEvnetDto, 1);
            mileage++;
        }
        if(reviewEvnetDto.getAttachedPhotoIds().size() > 0){                             // 2.
            commonReviewService.registerReviewEvents(reviewEvnetDto, 1);
            mileage++;
        }
        if(reviewRepository.findByPlaceId(reviewEvnetDto.getPlaceId()) == null){         // 3.
            commonReviewService.registerReviewEvents(reviewEvnetDto, 1);
            mileage++;
        }
        //리뷰 등록
        commonReviewService.registerReview(reviewEvnetDto, user);
        Review review = reviewRepository.findByReviewId(reviewEvnetDto.getReviewId());
        //사진 등록
        commonReviewService.registerPhotoList(reviewEvnetDto.getAttachedPhotoIds(), review);
        //user정보 업데이트
        user.updateMileage(mileage);
    }

    /*
     Review action이 DELETE일 경우
        1. 작성한 리뷰를 삭제하면 해당 리뷰로 부여한 내용 점수와 보너스 점수 회수
    */
    @Override
    @Transactional
    public void deleteReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEvnetDto){
        Review review = reviewRepository.findByReviewId(reviewEvnetDto.getReviewId());
        User user = userRepository.findByUserId(reviewEvnetDto.getUserId());
        //예외 처리
        if(review==null){
            throw new NotFoundReviewException();
        }

        List<ReviewEvent> reviewEventList = reviewEventRepository.findByReviewId(review.getReviewId());
        //해당 리뷰의 총 포인트 계산
        int mileage = -reviewEventRepository.sumPointSizeByReviewId(review.getReviewId());
        //유저 정보 업데이트
        user.updateMileage(user.getMileage() + mileage);            // 1.
        reviewRepository.delete(review);
        commonReviewService.registerReviewEvents(reviewEvnetDto, mileage);
    }

    /*
     Review action이 MOD일 경우
        1. 글이 없던 리뷰에 글을 작성하면 1점 부여
        2. 글이 있던 리뷰에 글을 다 지우면 1점 회수
        3. 글만 작성한 리뷰에 사진을 추가하면 1점 부여
        4. 사진이 있던 리뷰에 사진을 모두 삭제하면 1점 회수
        5. 수정 사항 업데이트(내용, 사진)
    */
    @Override
    @Transactional
    public void modifyReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEventDto) {
        Review review = reviewRepository.findByReviewId(reviewEventDto.getReviewId());
        User user = userRepository.findByUserId(reviewEventDto.getUserId());
        //예외 처리
        if(review == null){
            throw new NotFoundReviewException();
        }
        int mileage = 0;

        if(review.getContent().length() == 0 && reviewEventDto.getContent().length() != 0){     //TODO 1
            review.updateContent(reviewEventDto.getContent());
            commonReviewService.registerReviewEvents(reviewEventDto,1);
            mileage++;
        }else if(review.getContent().length() != 0 && reviewEventDto.getContent().length() == 0){ //TODO 2
            commonReviewService.registerReviewEvents(reviewEventDto,-1);
            mileage--;
        }
        if(review.getPhotoList().size() == 0 && reviewEventDto.getAttachedPhotoIds().size() != 0){//TODO 3
            commonReviewService.registerReviewEvents(reviewEventDto,1);
            mileage++;
        }else if(review.getPhotoList().size() != 0 && reviewEventDto.getAttachedPhotoIds().size() == 0){//TODO 4
            commonReviewService.registerReviewEvents(reviewEventDto,-1);
            mileage--;
        }

        review.updateContent(reviewEventDto.getContent());                                        //TODO 5
        List<Photo> photoList = photoRepository.findByReviewId(review.getReviewId());
        for(Photo photo : photoList){
            review.getPhotoList().remove(photo);
            photoRepository.delete(photo);
        }
        reviewRepository.save(review);
        commonReviewService.registerPhotoList(reviewEventDto.getAttachedPhotoIds(), review);
        user.updateMileage(user.getMileage() + mileage);
    }
}
