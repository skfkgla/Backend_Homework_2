package com.BackendHomework2.web;

import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.exception.error.NotFoundReviewActionTypeException;
import com.BackendHomework2.web.dto.CommonResponse;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventsService reviewEventsService;

    @PostMapping("/events")
    public ResponseEntity<CommonResponse> mileageEventRegistration(@RequestBody RequestReviewEvent.ReviewEvent eventDto){
        String message = "";
        switch (eventDto.getAction()) {
            case ADD:
                reviewEventsService.addReviewMileage(eventDto);
                message = "마일리지 추가 이벤트 성공";
                break;
            case DELETE:
                reviewEventsService.deleteReviewMileage(eventDto);
                message = "마일리지 삭제 이벤트 성공";
                break;
            case MOD:
                reviewEventsService.modifyReviewMileage(eventDto);
                message = "마일리지 수정 이벤트 성공";
                break;
            default:
                throw new NotFoundReviewActionTypeException();
        }
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message(message)
                .build(), HttpStatus.OK);
    }

}
