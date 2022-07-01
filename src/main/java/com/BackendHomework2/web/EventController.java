package com.BackendHomework2.web;

import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.web.dto.CommonResponse;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventsService reviewEventsService;

    @PostMapping("/events")
    public ResponseEntity<CommonResponse> mileageEventRegistration(@RequestBody RequestReviewEvent.ReviewEvent eventDto){
        reviewEventsService.addReviewMileage(eventDto);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("마일리지 이벤트 추가 성공")
                .build(), HttpStatus.OK);
    }

}