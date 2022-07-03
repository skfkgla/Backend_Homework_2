package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.UserMileageService;
import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import com.BackendHomework2.web.dto.ResponseUserMileage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test") // 테스트서버 프로파일 적용
public class UserMileageServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMileageService userMileageService;

    @Transactional
    @DisplayName("유저 총 마일리지 조회 테스트")
    @Test
    void reviewMileageAddTest() {
        User user = User.builder()
                .userId("userId")
                .mileage(3)
                .build();
        userRepository.save(user);

        ResponseUserMileage.UserMileage mileage = userMileageService.getUserMileage("userId").orElseThrow(()-> null);

        assertEquals(mileage.getUserMileage(),3);


    }
}
