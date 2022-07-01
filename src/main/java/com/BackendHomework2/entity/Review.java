package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "review")
@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_idx")
    private Long reviewIdx;

    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "content")
    private String content;

    @Column(name = "place_id")
    private String placeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToMany
    @JoinColumn(name="review_idx")
    private List<Photo> photoList = new ArrayList<>();

    @Builder
    public Review(String reviewId, String content, String placeId, User user){
        this.reviewId = reviewId;
        this.content = content;
        this.placeId = placeId;
        this.user = user;
    }
    public void addPhoto(Photo photo){
        this.photoList.add(photo);
    }
}
