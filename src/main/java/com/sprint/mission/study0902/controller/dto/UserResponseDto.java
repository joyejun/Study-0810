package com.sprint.mission.study0902.controller.dto;

import com.sprint.mission.study0902.repository.Message;
import com.sprint.mission.study0902.repository.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserResponseDto {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final String job;
    private final String specialty;
    private final LocalDateTime createAt;
    private final List<MessageResponseDto> messages;

    public static UserResponseDto from(User user, List<Message> messages) {
        return new UserResponseDto(user.getId(),
                user.getName(),
                user.getAge(),
                user.getJob(),
                user.getSpecialty(),
                user.getCreate_at(),
                messages.stream()
                        .map(MessageResponseDto::from)
                        .toList()
        );
    }
}
