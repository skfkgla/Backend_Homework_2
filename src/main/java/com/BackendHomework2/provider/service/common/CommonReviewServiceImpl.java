package com.BackendHomework2.provider.service.common;

import com.BackendHomework2.core.service.common.CommonReviewService;
import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.ReviewEvent;
import com.BackendHomework2.entity.User;
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
public class CommonReviewServiceImpl implements CommonReviewService {
    private final PhotoRepository photoRepository;
    private final ReviewEventRepository reviewEventRepository;
    private final ReviewRepository reviewRepository;


    //리뷰 등록

    @Override
    @Transactional
    public void registerReview(RequestReviewEvent.ReviewEventDto reviewEvnetDto, User user){
        Review review = Review.builder()
                .content(reviewEvnetDto.getContent())
                .placeId(reviewEvnetDto.getPlaceId())
                .reviewId(reviewEvnetDto.getReviewId())
                .user(user)
                .build();
        reviewRepository.save(review);
        user.addReview(review);
    }

    //포인트 이력 등록

    @Override
    @Transactional
    public void registerReviewEvents(RequestReviewEvent.ReviewEventDto reviewEvnetDto, int mileage) {
        ReviewEvent reviewEvent = ReviewEvent.builder()
                .action(reviewEvnetDto.getAction())
                .reviewId(reviewEvnetDto.getReviewId())
                .pointSize(mileage)
                .build();
        reviewEventRepository.save(reviewEvent);
    }

    //사진 등록(리스트형)

    @Override
    @Transactional
    public void registerPhotoList(List<String> photoIds, Review review){
        for(String photoId : photoIds){
            Photo photo = com.BackendHomework2.entity.Photo.builder()
                    .photoId(photoId)
                    .review(review)
                    .build();
            photoRepository.save(photo);
            review.addPhoto(photo);
        }
    }

    //사진 등록

    @Override
    @Transactional
    public void registerPhoto(String photoId, Review review){
            Photo photo = com.BackendHomework2.entity.Photo.builder()
                    .photoId(photoId)
                    .review(review)
                    .build();
            photoRepository.save(photo);
            review.addPhoto(photo);
    }
}
