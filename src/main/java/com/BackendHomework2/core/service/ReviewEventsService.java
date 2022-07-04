package com.BackendHomework2.core.service;

import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface ReviewEventsService {
    void addReviewAndMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
    void deleteReviewAndMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
    void modifyReviewAndMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
}
