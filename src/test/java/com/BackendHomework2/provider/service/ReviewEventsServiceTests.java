package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.ReviewEventsService;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 테스트서버 프로파일 적용
public class ReviewEventsServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ReviewEventRepository reviewEventRepository;
    @Autowired
    private ReviewEventsService reviewEventsService;


    @Transactional
    @DisplayName("리뷰와 마일리지 등록 테스트")
    @Test
    void addReviewAndMileageTest() {
        long startTime = System.currentTimeMillis();
        //given
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);
        List<String> Photo= new ArrayList<>();
        Photo.add("photo number 1");
        Photo.add("photo number 2");
        RequestReviewEvent.ReviewEventDto eventDto = RequestReviewEvent.ReviewEventDto.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(Photo)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        //when
        reviewEventsService.addReviewAndMileage(eventDto);
        Review review = reviewRepository.findByReviewId("reviewId");
        User userObject = userRepository.findByUserId("userId");
        List<com.BackendHomework2.entity.Photo> photo = photoRepository.findByReviewId("reviewId");
        List<ReviewEvent> reviewEventList = reviewEventRepository.findByReviewId("reviewId");
        //then
        assertEquals(review.getReviewId(),"reviewId");
        assertEquals(review.getContent(),"좋아요!");   //review에 제대로 들어가는지 확인
        assertEquals(userObject.getMileage(),3);    //user에 제대로 마일리지가 적립 되는지
        assertEquals(photo.get(0).getPhotoId(),"photo number 1");   //photo에 제대로 들어가는지 reviewId로 찾았기 때문에 review가 매핑되어 있는지는 확인 불필요
        assertEquals(reviewEventList.size(), 3);    //리뷰 마일리지 ADD 이벤트가 조건 1,2,3을 충족하기 때문에 3개가 들어가야함

        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
    }
    @Transactional
    @DisplayName("리뷰와 마일리지 삭제 테스트")
    @Test
    void deleteReviewAndMileageTest() {
        long startTime = System.currentTimeMillis();
        //given
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);
        List<String> Photo= new ArrayList<>();
        Photo.add("photo number 1");
        Photo.add("photo number 2");
        RequestReviewEvent.ReviewEventDto eventDtoNumber1 = RequestReviewEvent.ReviewEventDto.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(Photo)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        reviewEventsService.addReviewAndMileage(eventDtoNumber1);  // addReview 쓰임
        //when
        reviewEventsService.deleteReviewAndMileage(eventDtoNumber1);
        Review review = reviewRepository.findByReviewId("reviewId");
        //then
        assertNull(review);
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
    }

    @Transactional
    @DisplayName("리뷰와 마일리지 수정 테스트")
    @Test
    void modifyReviewAndMileageTest() {
        long startTime = System.currentTimeMillis();
        //given
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);
        List<String> Photo = new ArrayList<>();
        Photo.add("photo number 1");
        Photo.add("photo number 2");
        List<String> nullPhoto = new ArrayList<>();
        RequestReviewEvent.ReviewEventDto eventDtoMileage1 = RequestReviewEvent.ReviewEventDto.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(Photo)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        RequestReviewEvent.ReviewEventDto eventDtoMileage2 = RequestReviewEvent.ReviewEventDto.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.MOD)
                .attachedPhotoIds(nullPhoto)
                .content("싫어요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        reviewEventsService.addReviewAndMileage(eventDtoMileage1);  // addReview 쓰임
        //when
        reviewEventsService.modifyReviewAndMileage(eventDtoMileage2);
        //then
        assertEquals(2,user.getMileage());
        assertEquals(4,reviewEventRepository.findByReviewId("reviewId").size());
        assertEquals(reviewRepository.findByReviewId("reviewId").getContent(),"싫어요!");
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
    }

}
