package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
public class User {
    //userId를 받을 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name="user_id")
    private String userId;

    @Column(name="mileage")
    private int mileage;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @Builder
    public User(String userId, int mileage){
        this.userId = userId;
        this.mileage = mileage;
    }
    public void addReview(Review review){
        this.reviewList.add(review);
    }
    public void updateMileage(int mileage){
        this.mileage = mileage;
    }
}