package com.BackendHomework2.web;


import com.BackendHomework2.core.service.UserMileageService;
import com.BackendHomework2.exception.error.NotFoundMileageException;
import com.BackendHomework2.web.dto.common.CommonResponse;
import com.BackendHomework2.web.dto.ResponseUserMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserMileageController {
    private final UserMileageService userMileageService;

    @GetMapping("/point")
    public ResponseEntity<CommonResponse> getUserMileage(@RequestParam Map<String,String> map){
        ResponseUserMileage.UserMileageDto userMileage = userMileageService.getUserMileage(map.get("userId")).orElseThrow(()-> new NotFoundMileageException());

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("포인트 총점 조회 성공")
                .list(userMileage)
                .build(), HttpStatus.OK);
    }
}
