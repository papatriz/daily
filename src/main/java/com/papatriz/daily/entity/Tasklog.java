package com.papatriz.daily.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasklog")
public class Tasklog {
    @EmbeddedId
    private TasklogId id;

    @MapsId("actId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "act_id", nullable = false)
    private Activity activity;

    public TasklogId getId() {
        return id;
    }

    public void setId(TasklogId id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}