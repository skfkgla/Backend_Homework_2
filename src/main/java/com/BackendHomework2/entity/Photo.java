package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "photo")
@Entity
@Getter
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="photo_idx")
    private Long id;

    @Column(name = "photo_id")
    private String photoId;

    @Column(name = "review_id")
    private String reviewId;

    @Builder
    public Photo(String photoId, String reviewId){
        this.photoId = photoId;
        this.reviewId = reviewId;
    }

}
