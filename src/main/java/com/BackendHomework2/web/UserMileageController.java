package com.BackendHomework2.web;


import com.BackendHomework2.core.service.UserMileageService;
import com.BackendHomework2.exception.error.NotFoundUserException;
import com.BackendHomework2.web.dto.CommonResponse;
import com.BackendHomework2.web.dto.ResponseUserMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserMileageController {
    private final UserMileageService userMileageService;

    @GetMapping("/point")
    public ResponseEntity<CommonResponse> getUserMileage(@RequestBody Map<String,String> userId){
        ResponseUserMileage.UserMileage userMileage = userMileageService.getUserMileage(userId.get("userId")).orElseThrow(()-> null);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("포인트 총점 조회 성공")
                .list(userMileage)
                .build(), HttpStatus.OK);
    }
}
