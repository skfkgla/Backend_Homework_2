package com.BackendHomework2.provider.service;

import com.BackendHomework2.core.service.UserMileageService;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.exception.error.NotFoundUserException;
import com.BackendHomework2.repository.UserRepository;
import com.BackendHomework2.web.dto.ResponseUserMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserMileageServiceImpl implements UserMileageService {
    private final UserRepository userRepository;

    /*
    TODO
     유저 포인트 총점 조회 서비스
    */

    @Override
    @Transactional(readOnly = true)
    public Optional<ResponseUserMileage.UserMileage> getUserMileage(String userId){
            User user = userRepository.findByUserId(userId);
            if(user == null){
                throw new NotFoundUserException();
            }
            ResponseUserMileage.UserMileage mileage = ResponseUserMileage.UserMileage.builder()
                    .userId(userId)
                    .userMileage(user.getMileage())
                    .build();
        return Optional.ofNullable(mileage);
    }
}
