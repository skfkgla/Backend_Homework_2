package com.BackendHomework2.repository;

import com.BackendHomework2.entity.Review;
import com.BackendHomework2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewId(String reviewId);
    Review findByPlaceId(String reviewId);

    @Query("select r from Review r " +
            "join r.user u " +
            "where u.userId = :userId " +
            "and r.placeId = :placeId")
    Review findByPlaceIdAndUserId(String placeId, String userId);
}
