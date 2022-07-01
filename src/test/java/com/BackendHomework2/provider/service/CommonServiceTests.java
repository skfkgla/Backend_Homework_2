package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.common.CommonService;
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
import org.hibernate.annotations.DynamicUpdate;
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
public class CommonServiceTests {
    @Autowired
    private CommonService commonService;
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

        commonService.registerPhoto(Photo, "reviewId");
        List<com.BackendHomework2.entity.Photo> photoList = photoRepository.findByReviewId("reviewId");

        //위의 추가한 리스트와 같은지 테스트
        for(int i=0; i < photoList.size(); i++){
            assertEquals(photoList.get(i).getPhotoId(),"photo number "+i);
            assertEquals(photoList.get(i).getReviewId(),"reviewId");
        }
    }
    @Transactional
    @DisplayName("포인트 이력 등록 테스트")
    @Test
    void registerReviewEventsTest() {
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);
        List<String> photoList = new ArrayList<>();
            Photo photo = Photo.builder()
                    .photoId("photo")
                    .reviewId("reviewId")
                    .build();
            photoList.add("photo");

        RequestReviewEvent.ReviewEvent eventDto = RequestReviewEvent.ReviewEvent.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(photoList)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();

        commonService.registerReviewEvents(eventDto, 3);
        List<ReviewEvent> reviewEvents = reviewEventRepository.findByReviewId("reviewId");
        assertNotNull(reviewEvents);
    }
    @Transactional
    @DisplayName("마일리지 등록 테스트")
    @Test
    void updateMileageTest() {
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);

        Photo photo = Photo.builder()
                .reviewId("reviewId")
                .photoId("photoId")
                .build();
        photoRepository.save(photo);

        Review review = Review.builder()
                .content("좋아요")
                .placeId("제주도")
                .user(user)
                .reviewId("reviewId")
                .build();
        reviewRepository.save(review);
        review.addPhoto(photo);

        user.updateMileage(3+ user.getMileage());
        user.addReview(review);

        User userObject = userRepository.findByUserId(user.getUserId());
        assertEquals(userObject.getMileage(),3);


    }
}
