package com.BackendHomework2.core.service.common;

import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface CommonService {
    void registerReviewEvents(RequestReviewEvent.ReviewEvent reviewEvnetDto, int mileage);
    void registerPhoto(List<String> photoIds, String review);
}
