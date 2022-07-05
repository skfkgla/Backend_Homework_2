package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "users", indexes = {
        @Index(name = "idx_user__userId", columnList = "user_id")
})
@Entity
@Getter
@NoArgsConstructor
public class User {
    //userId를 받을 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
