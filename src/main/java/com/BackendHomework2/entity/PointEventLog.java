package com.BackendHomework2.entity;

import com.BackendHomework2.core.type.EventType;
import com.BackendHomework2.core.type.ReviewActionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "point_event_logs")
@Entity
@Getter
@NoArgsConstructor
public class PointEventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "action_type")
    private ReviewActionType actionType;

    @Column(name = "point")
    private int point;

    @Column(name = "event_type")
    private EventType eventType;

    @Builder
    public PointEventLog(ReviewActionType actionType, int point, EventType eventType){
        this.actionType = actionType;
        this.point = point;
        this.eventType = eventType;
    }
}
