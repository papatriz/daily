package com.papatriz.daily.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "activity")
@Setter
@Getter
public class Activity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title", nullable = false, length = 127)
    private String title;

    @Column(name = "duration", nullable = false)
    private Short duration;

    @Column(name = "weight", nullable = false)
    private Short weight;

}