package com.BackendHomework2.web;

import com.BackendHomework2.core.service.ReviewEventsService;
import com.BackendHomework2.exception.error.NotFoundReviewActionTypeException;
import com.BackendHomework2.web.dto.common.CommonResponse;
import com.BackendHomework2.web.dto.RequestReviewEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewEventsService reviewEventsService;

    @PostMapping("/events")
    public ResponseEntity<CommonResponse> mileageEventRegistration(@Valid @RequestBody RequestReviewEvent.ReviewEventDto eventDto){
        String message = "";
        switch (eventDto.getAction()) {
            case ADD:
                reviewEventsService.addReviewAndMileage(eventDto);
                message = "리뷰 추가 이벤트 성공";
                break;
            case DELETE:
                reviewEventsService.deleteReviewAndMileage(eventDto);
                message = "리뷰 삭제 이벤트 성공";
                break;
            case MOD:
                reviewEventsService.modifyReviewAndMileage(eventDto);
                message = "리뷰 수정 이벤트 성공";
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
