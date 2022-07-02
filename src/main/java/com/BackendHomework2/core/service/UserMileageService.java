package com.BackendHomework2.core.service;


import com.BackendHomework2.web.dto.RequestReviewEvent;
import com.BackendHomework2.web.dto.ResponseUserMileage;

import java.util.Optional;

public interface UserMileageService {
    Optional<ResponseUserMileage.UserMileage> getUserMileage(String userId);
}
