package com.pundix.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPersisted() {
        return id != null;
    }

    public boolean isNotPersisted() {
        return !isPersisted();
    }
}
