package com.sprint.mission.study0902.controller.dto;

import com.sprint.mission.study0902.repository.Message;
import com.sprint.mission.study0902.repository.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class MessageResponseDto {
    private final Integer id;
    private final String message;
    private final LocalDateTime createdAt;

    public static MessageResponseDto from(Message entity) {
        return new MessageResponseDto(
                entity.getId(),
                entity.getMessage(),
                entity.getCreated_at()
        );
    }
}
