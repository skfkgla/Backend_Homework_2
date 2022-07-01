package com.BackendHomework2.repository;

import com.BackendHomework2.entity.ReviewEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewEventRepository extends JpaRepository<ReviewEvent, Long> {
    List<ReviewEvent> findByReviewId(String reviewId);
}
