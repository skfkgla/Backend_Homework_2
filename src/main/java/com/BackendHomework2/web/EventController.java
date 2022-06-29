package com.BackendHomework2.web;

import com.BackendHomework2.provider.service.PointEventsServiceImpl;
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

    private final PointEventsServiceImpl pointEventsServiceImpl;

    @PostMapping("/events")
    public ResponseEntity<CommonResponse> getPoint(@RequestBody RequestReviewEvent.ReviewEvent eventDto){


        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("")
                .build(), HttpStatus.OK);
    }


}
