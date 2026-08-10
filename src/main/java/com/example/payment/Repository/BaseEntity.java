package com.example.payment.Repository;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class BaseEntity {

    private Integer id;
    private boolean deleted = false;

    private LocalDateTime createdAT;
    private       Integer createdBy;
    private LocalDateTime updatedAT;
    private       Integer updatedBy;

    protected BaseEntity(Integer id, Integer userId) {
        this.id = id;
        //deleted
        this.createdAT = LocalDateTime.now();
        this.createdBy = userId;
        this.updatedAT = LocalDateTime.now();
        this.updatedBy = userId;
    }

    protected void updated(Integer userId) {
        this.updatedAT = LocalDateTime.now();
        this.updatedBy = userId;
    }
}
