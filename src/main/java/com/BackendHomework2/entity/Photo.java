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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_idx")
    private Long id;

    @Column(name = "photo_id")
    private String photoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_idx")
    private Review review;

    @Builder
    public Photo(String photoId, Review review){
        this.photoId = photoId;
        this.review = review;
    }
    public void deleteReviewMapping(Review review){
        this.review = null;
    }

}
