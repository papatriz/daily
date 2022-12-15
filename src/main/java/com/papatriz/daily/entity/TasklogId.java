package com.papatriz.daily.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class TasklogId implements Serializable {
    private static final long serialVersionUID = -273243308358019484L;
    @Column(name = "act_id", nullable = false)
    private Long actId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TasklogId entity = (TasklogId) o;
        return Objects.equals(this.date, entity.date) &&
                Objects.equals(this.actId, entity.actId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, actId);
    }

}