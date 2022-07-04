package com.BackendHomework2.provider.service.common;

import com.BackendHomework2.core.service.common.CommonReviewService;
import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.ReviewEvent;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.PhotoRepository;
import com.BackendHomework2.repository.ReviewEventRepository;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test") // 테스트서버 프로파일 적용
public class CommonReviewServiceTests {
    @Autowired
    private CommonReviewService commonReviewService;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewEventRepository reviewEventRepository;

    @Transactional
    @DisplayName("사진 등록 테스트")
    @Test
    void registerPhotoTest() {
        List<String> Photo = new ArrayList<>();
        for(int i=0; i< 10; i++){
            Photo.add("photo number "+i);
        }
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);

        Review jejuReview = Review.builder()
                .content("좋아요")
                .placeId("제주도")
                .user(user)
                .reviewId("jeju")
                .build();
        reviewRepository.save(jejuReview);
        Review udoReview = Review.builder()
                .content("좋아요")
                .placeId("우도")
                .user(user)
                .reviewId("udo")
                .build();
        reviewRepository.save(udoReview);

        commonReviewService.registerPhoto("photoId", udoReview);
        commonReviewService.registerPhotoList(Photo, jejuReview);
        List<com.BackendHomework2.entity.Photo> jejuPhotoList = photoRepository.findByReviewId("jeju");

        //위의 추가한 리스트와 같은지 테스트
        for(int i=0; i < jejuPhotoList.size(); i++){
            assertEquals(jejuPhotoList.get(i).getPhotoId(),"photo number "+i);
        }
        Photo udoPhoto = photoRepository.findByPhotoId("photoId");
        assertNotNull(udoPhoto);                                    //한개의 사진 등록 테스트
    }
    @Transactional
    @DisplayName("포인트 이력 등록 테스트")
    @Test
    void registerReviewEventsTest() {
        //given
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);

        Review review = Review.builder()
                .content("좋아요")
                .placeId("제주도")
                .user(user)
                .reviewId("reviewId")
                .build();
        reviewRepository.save(review);

        List<String> photoList = new ArrayList<>();
            Photo photo = Photo.builder()
                    .photoId("photo")
                    .review(review)
                    .build();
            photoList.add("photo");

        RequestReviewEvent.ReviewEventDto eventDto = RequestReviewEvent.ReviewEventDto.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(photoList)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        //when
        commonReviewService.registerReviewEvents(eventDto, 1);
        List<ReviewEvent> reviewEvents = reviewEventRepository.findByReviewId("reviewId");
        //then
        assertNotNull(reviewEvents);
    }
    @Transactional
    @DisplayName("마일리지 업데이트 테스트")
    @Test
    void updateMileageTest() {
        //given
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);

        Review review = Review.builder()
                .content("좋아요")
                .placeId("제주도")
                .user(user)
                .reviewId("reviewId")
                .build();
        reviewRepository.save(review);

        Photo photo = Photo.builder()
                .review(review)
                .photoId("photoId")
                .build();
        photoRepository.save(photo);

        review.addPhoto(photo);
        //when
        user.updateMileage(3+ user.getMileage());
        user.addReview(review);
        User userObject = userRepository.findByUserId(user.getUserId());
        //then
        assertEquals(userObject.getMileage(),3);


    }
}
