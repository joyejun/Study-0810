package com.sprint.mission.study0902.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateRequestDto {
    private final String name;
    private final Integer age;
    private final String job;
    private final String specialty = "EMPTY";
}
