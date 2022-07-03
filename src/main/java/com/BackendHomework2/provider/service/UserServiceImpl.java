package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.UserService;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // TODO 회원 등록 서비스

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
