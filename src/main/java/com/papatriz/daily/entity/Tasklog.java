package com.papatriz.daily.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasklog")
@Setter
@Getter
public class Tasklog {
    @EmbeddedId
    private TasklogId id;

    @MapsId("actId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "act_id", nullable = false)
    private Activity activity;

}