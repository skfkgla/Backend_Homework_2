package com.BackendHomework2.repository;

import com.BackendHomework2.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, String> {
    @Query("select p from Photo p " +
            "join p.review r " +
            "where r.reviewId = :reviewId")
    List<Photo> findByReviewId(String reviewId);

    @Query("select p from Photo p " +
            "join p.review r " +
            "where p.photoId = :photoId "+
            "and r.reviewId = :reviewId")
    Photo findByPhotoIdAndReviewId(String photoId, String reviewId);

    Photo findByPhotoId(String photoId);
}
