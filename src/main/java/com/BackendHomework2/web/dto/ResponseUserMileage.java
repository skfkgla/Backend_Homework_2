package com.BackendHomework2.web.dto;

import lombok.Builder;
import lombok.Data;

public class ResponseUserMileage {
    @Builder
    @Data
    public static class UserMileage {
        private String userId;
        private int userMileage;
    }
}
