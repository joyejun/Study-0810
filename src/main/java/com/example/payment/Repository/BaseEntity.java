package com.example.payment.Repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public abstract class BaseEntity {

    protected Integer id;
    protected boolean deleted = false;

    protected LocalDateTime createdAT;
    protected       Integer createdBy;
    protected LocalDateTime updatedAT;
    protected       Integer updatedBy;

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
