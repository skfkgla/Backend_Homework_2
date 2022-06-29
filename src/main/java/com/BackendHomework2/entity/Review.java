package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "review")
@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "content")
    private String content;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "attached_photo_ids")
    private String attachedPhotoIds;

    @Builder
    public Review(String reviewId, String content, String placeId, String attachedPhotoIds){
        this.reviewId = reviewId;
        this.content = content;
        this.placeId = placeId;
        this.attachedPhotoIds = attachedPhotoIds;
    }
}
