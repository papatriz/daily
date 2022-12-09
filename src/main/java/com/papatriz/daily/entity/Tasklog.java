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
    private Activity act;

    public TasklogId getId() {
        return id;
    }

    public void setId(TasklogId id) {
        this.id = id;
    }

    public Activity getAct() {
        return act;
    }

    public void setAct(Activity act) {
        this.act = act;
    }

}