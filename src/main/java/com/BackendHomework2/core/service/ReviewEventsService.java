package com.BackendHomework2.core.service;

import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface ReviewEventsService {
    void addReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEvnetDto);
    void deleteReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEvnetDto);
    void modifyReviewAndMileage(RequestReviewEvent.ReviewEventDto reviewEvnetDto);
}
