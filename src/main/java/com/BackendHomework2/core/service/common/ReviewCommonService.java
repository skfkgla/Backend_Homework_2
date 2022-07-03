package com.BackendHomework2.core.service.common;

import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface ReviewCommonService {
    Review registerReview(RequestReviewEvent.ReviewEvent reviewEvnetDto, User user);
    void registerReviewEvents(RequestReviewEvent.ReviewEvent reviewEvnetDto, int mileage);
    public void registerPhoto(List<String> photoIds, Review review);
    void registerPhoto(String photoId,Review review);
}
