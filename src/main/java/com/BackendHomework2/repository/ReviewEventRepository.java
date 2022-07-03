package com.BackendHomework2.repository;

import com.BackendHomework2.entity.ReviewEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewEventRepository extends JpaRepository<ReviewEvent, String> {
    List<ReviewEvent> findByReviewId(String reviewId);

    @Query(value = "select sum(pointSize) from ReviewEvent r "+
            "where r.reviewId = :reviewId")
    int sumPointSizeByReviewId(String reviewId);
}