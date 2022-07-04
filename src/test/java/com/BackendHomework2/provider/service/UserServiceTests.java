package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.UserService;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test") // 테스트서버 프로파일 적용
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Transactional
    @DisplayName("회원 등록 테스트")
    @Test
    void registerUserTest() {
        String userId = "userId";
        userService.registerUser(userId);
        User user = userRepository.findByUserId("userId");

        assertNotNull(user);
        assertEquals(user.getMileage(),0);
    }
}
