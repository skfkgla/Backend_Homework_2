package com.BackendHomework2.core.service;

import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface ReviewEventsService {
    void addReviewMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
    void deleteReviewMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
    void modifyReviewMileage(RequestReviewEvent.ReviewEvent reviewEvnetDto);
}
