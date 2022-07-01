package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.RegisterUserService;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {
    private final UserRepository userRepository;

    // TODO 회원 등록

    @Override
    @Transactional
    public void registerUser(String userId){
        User user = User.builder()
                .userId(userId)
                .mileage(0)
                .build();
        userRepository.save(user);
    }
}
