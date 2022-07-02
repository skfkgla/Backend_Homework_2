package com.BackendHomework2.repository;

import com.BackendHomework2.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p from Photo p " +
            "join p.review r " +
            "where r.reviewId = :reviewId")
    List<Photo> findByReviewId(String reviewId);
}
