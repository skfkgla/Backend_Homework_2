package com.BackendHomework2.web.dto;

import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class RequestReviewEvent {
    @Builder
    @Data
    public static class ReviewEvent {
        private MileageEventType type;
        private ReviewActionType action;
        private String reviewId;
        private String content;
        private List<String> attachedPhotoIds;
        private String userId;
        private String placeId;
    }
}
