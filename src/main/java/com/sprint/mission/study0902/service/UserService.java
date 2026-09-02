package com.sprint.mission.study0902.service;

import com.sprint.mission.study0902.controller.dto.UserCreateRequestDto;
import com.sprint.mission.study0902.controller.dto.UserResponseDto;
import com.sprint.mission.study0902.repository.Message;
import com.sprint.mission.study0902.repository.MessageJdbcApiRepository;
import com.sprint.mission.study0902.repository.User;
import com.sprint.mission.study0902.repository.UserJdbcApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJdbcApiRepository userJdbcApiRepository;
    private final MessageJdbcApiRepository messageJdbcApiRepository;

    public UserResponseDto findById(Integer id) throws SQLException {
        User retrive = userJdbcApiRepository.findById(id);
        List<Message> retrivedMessage = messageJdbcApiRepository.findById(id);
        return UserResponseDto.from(retrive, retrivedMessage);
    }

    public UserResponseDto create(UserCreateRequestDto request) throws SQLException {
        User createUser = userJdbcApiRepository.create(
                request.getName(),
                request.getAge(),
                request.getJob(),
                request.getSpecialty());
        Message createMessage = messageJdbcApiRepository.create(createUser.getId(), createUser.getName());
        return UserResponseDto.from(createUser, Collections.singletonList(createMessage));
    }

}
