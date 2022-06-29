package com.BackendHomework2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor
public class User {
    //userId를 받을 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_idx")
    private Long id;

    @Column(name="user_id")
    private String userId;

    @Column(name="point")
    private int point;

    @Builder
    public User(String userId, int point){
        this.userId = userId;
        this.point = point;
    }
}
