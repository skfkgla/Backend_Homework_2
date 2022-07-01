package com.BackendHomework2.provider.service;


import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.core.service.common.CommonService;
import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.exception.error.DuplicateReviewException;
import com.BackendHomework2.exception.error.NotFoundMileageTypeException;
import com.BackendHomework2.repository.PhotoRepository;
import com.BackendHomework2.repository.ReviewEventRepository;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewEventsServiceImpl implements ReviewEventsService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final PhotoRepository photoRepository;
    private final ReviewEventRepository reviewEventRepository;
    private final CommonService commonService;
    /*
    TODO
     Review action이 ADD일 경우
        1. 1자 이상의 리뷰 1점 추가
        2. 1장 이상의 사진 1점 추가
        3. 특정 장소에서 첫 리뷰라면 1점 추가(현재 시간 특정장소에 리뷰가 없으면)
     조건 - 한 장소에 리뷰를 남겼다면 다시 똑같은 장소에 리뷰를 남길 수 없음
    */
    @Override
    @Transactional
    public void addReviewMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto){
        //예외 처리
        if(reviewEvnetDto.getType() != MileageEventType.REVIEW){    //마일리지 이벤트 타입이 REVIEW가 아니면 예외 던지기
            throw new NotFoundMileageTypeException();
        }
        if(null != reviewRepository.findByPlaceIdAndUserId(reviewEvnetDto.getPlaceId(),reviewEvnetDto.getUserId())){    //똑같은 곳에 리뷰를 남기면 예외 던지기
            throw new DuplicateReviewException();
        }

        User user = userRepository.findByUserId(reviewEvnetDto.getUserId());    //리뷰를 남긴 유저 조회
        int mileage = 0;

        if(reviewEvnetDto.getContent().length() > 0){                                    // TODO 1
            mileage++;
        }
        if(reviewEvnetDto.getAttachedPhotoIds().size() > 0){                             // TODO 2
            mileage++;
        }
        if(reviewRepository.findByReviewId(reviewEvnetDto.getPlaceId()) == null){        // TODO 3
            mileage++;
        }
        commonService.registerPhoto(reviewEvnetDto.getAttachedPhotoIds(),reviewEvnetDto.getReviewId());   //사진 등록
        commonService.registerReviewEvents(reviewEvnetDto, mileage);

        //리뷰 등록
        Review review = Review.builder()
                .content(reviewEvnetDto.getContent())
                .placeId(reviewEvnetDto.getPlaceId())
                .reviewId(reviewEvnetDto.getReviewId())
                .user(user)
                .build();
        reviewRepository.save(review);
        for(Photo photo: photoRepository.findByReviewId(reviewEvnetDto.getReviewId())){
            review.addPhoto(photo);
        }

        user.updateMileage(mileage+user.getMileage());
        user.addReview(review);

    }
}
