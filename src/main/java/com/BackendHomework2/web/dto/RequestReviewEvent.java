package com.BackendHomework2.web.dto;

import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RequestReviewEvent {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewEventDto {
        @NotNull(message = "이벤트 타입이 입력되지 않았습니다.")
        private MileageEventType type;
        @NotNull(message = "리뷰 액션 타입이 입력되지 않았습니다.")
        private ReviewActionType action;
        @NotNull(message = "리뷰 아이디가 입력되지 않았습니다.")
        private String reviewId;
        @NotNull(message = "내용이 없다면 빈 문자열로 넣어주세요")
        private String content;
        @NotNull(message = "사진을 빈배열로 넣어주세요")
        private List<String> attachedPhotoIds;
        @NotNull(message = "유저 아이디가 입력되지 않았습니다.")
        private String userId;
        @NotNull(message = "장소 아이디가 입력되지 않았습니다.")
        private String placeId;
    }
}
