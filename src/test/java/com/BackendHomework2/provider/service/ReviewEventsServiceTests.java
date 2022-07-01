package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.ReviewRepository;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test") // 테스트서버 프로파일 적용
public class ReviewEventsServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewEventsService reviewEventsService;

    @Transactional
    @DisplayName("마일리지 등록 테스트")
    @Test
    void reviewMileageAddTest() {
        User user = User.builder()
                .userId("userId")
                .mileage(0)
                .build();
        userRepository.save(user);
        List<String> Photo= new ArrayList<>();
        Photo.add("photo number 1");
        Photo.add("photo number 2");
        RequestReviewEvent.ReviewEvent eventDto = RequestReviewEvent.ReviewEvent.builder()
                .userId("userId")
                .reviewId("reviewId")
                .action(ReviewActionType.ADD)
                .attachedPhotoIds(Photo)
                .content("좋아요!")
                .placeId("제주도")
                .type(MileageEventType.REVIEW)
                .build();
        reviewEventsService.addReviewMileage(eventDto);

        Review review = reviewRepository.findByReviewId("reviewId");
        System.out.println(review.getReviewIdx()+" "+review.getReviewId()+" "+review.getContent()+" "+""+review.getUser());
    }

}
