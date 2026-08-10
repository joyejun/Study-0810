package com.example.payment.Repository;

import java.time.LocalDateTime;

public class BaseEntity {
    private Integer id;
    private boolean deleted = false;

    private LocalDateTime createdAt;
    private Integer createdBt;
    private LocalDateTime updatedAt;
    private Integer updateBt;
}
