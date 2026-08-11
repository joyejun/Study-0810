package com.example.payment.Controller.dto.User;

import com.example.payment.Repository.user.UserGrade;
import com.example.payment.internal.api.dto.RequestingUserDto;
import lombok.Getter;

@Getter
public class UserAdminUpdateRequestDto extends RequestingUserDto {

    private final String name;
    private final UserGrade grade;
    private final int point;

    public UserAdminUpdateRequestDto(String name, UserGrade grade, int point, Integer requestUserId) {
        super(requestUserId);
        this.name = name;
        this.grade = grade;
        this.point = point;
    }
}
