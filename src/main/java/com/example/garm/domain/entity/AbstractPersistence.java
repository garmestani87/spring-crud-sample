package com.example.garm.domain.entity;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class AbstractPersistence<L extends Serializable> {

    public static final int ALLOCATION_SIZE = 1;
    public static final String DEFAULT_SEQ_GEN = "DEFAULT_SEQ_GEN";
    public static final String SEQUENCE_STRATEGY = "sequence";
    public static final String ASSIGNED_STRATEGY = "assigned";

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(generator = DEFAULT_SEQ_GEN)
    protected L id;

    @Column(name = "INSERT_DATE", updatable = false)
    protected LocalDateTime insertDate;

    @Column(name = "MODIFY_DATE")
    protected LocalDateTime modifyDate;

    @Version
    @Column(name = "VERSION")
    protected Integer version;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && Hibernate.getClass(this) == Hibernate.getClass(o)) {
            AbstractPersistence<?> that = (AbstractPersistence) o;
            if (this.getId() == null || that.getId() == null)
                return false;
            else
                return Objects.equals(this.getId(), that.getId());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
