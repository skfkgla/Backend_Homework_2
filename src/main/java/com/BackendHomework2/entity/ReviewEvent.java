package com.BackendHomework2.entity;

import com.BackendHomework2.core.type.MileageEventType;
import com.BackendHomework2.core.type.ReviewActionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "review_event")
@Entity
@Getter
@NoArgsConstructor
public class ReviewEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "review_id") //해당 유저의 review_id로 전체를 조회할 수 있도록 하면 연관관계가 필요 없음
    private String reviewId;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ReviewActionType action;

    @Column(name = "point_size")
    private int pointSize;

    @Column(name = "create_at")
    private Date createAt = new Date(); // date

    @Builder
    public ReviewEvent(ReviewActionType action, int pointSize, String reviewId){
        this.action = action;
        this.pointSize = pointSize;
        this.reviewId = reviewId;
    }
}
