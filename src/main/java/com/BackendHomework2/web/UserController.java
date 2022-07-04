package com.BackendHomework2.web;

import com.BackendHomework2.core.service.UserService;
import com.BackendHomework2.web.dto.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user/register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody Map<String,String> map){
        userService.registerUser(map.get("userId"));

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("회원 등록 성공")
                .build(), HttpStatus.OK);
    }
}
