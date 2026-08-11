package com.example.payment.Controller.dto.User;

import com.example.payment.Repository.user.User;
import com.example.payment.internal.api.dto.RequestingUserDto;

public class UserAdminCreateRequestDto extends RequestingUserDto {
    private final String name;

    public UserAdminCreateRequestDto(String name, Integer requestUserId) {
        super(requestUserId);
        this.name = name;
    }
    public User to() {
        return User.create(this.name, super.requestUserId);
    }
}
