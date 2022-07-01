package com.BackendHomework2.web;

import com.BackendHomework2.core.service.RegisterUserService;
import com.BackendHomework2.web.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRegisterController {
    private final RegisterUserService registerUserService;
    @PostMapping("/user/register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody Map<String,String> userId){
        registerUserService.registerUser(userId.get("userId"));

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("회원 등록 성공")
                .build(), HttpStatus.OK);
    }
}
