package com.BackendHomework2.core.service.common;

import com.BackendHomework2.entity.Photo;
import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import com.BackendHomework2.web.dto.RequestReviewEvent;

import java.util.List;

public interface CommonReviewService {
    void registerReview(RequestReviewEvent.ReviewEventDto reviewEvnetDto, User user);
    void registerReviewEvents(RequestReviewEvent.ReviewEventDto reviewEvnetDto, int mileage);
    void registerPhotoList(List<String> photoIds, Review review);
    void registerPhoto(String photoId,Review review);
}
