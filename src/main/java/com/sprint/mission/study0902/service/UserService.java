package com.sprint.mission.study0902.service;

import com.sprint.mission.study0902.controller.dto.UserCreateRequestDto;
import com.sprint.mission.study0902.controller.dto.UserResponseDto;
import com.sprint.mission.study0902.repository.Message;
import com.sprint.mission.study0902.repository.MessageJdbcApiRepository;
import com.sprint.mission.study0902.repository.User;
import com.sprint.mission.study0902.repository.UserJdbcApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final DataSource dataSource;
    private final UserJdbcApiRepository userJdbcApiRepository;
    private final MessageJdbcApiRepository messageJdbcApiRepository;

    public UserResponseDto findById(Integer id) throws SQLException {
        User retrive = userJdbcApiRepository.findById(id);
        List<Message> retrivedMessage = messageJdbcApiRepository.findById(id);
        return UserResponseDto.from(retrive, retrivedMessage);
    }

    public UserResponseDto create(UserCreateRequestDto request) throws SQLException {
        //collection 획득한 곳에서
        Connection connection = dataSource.getConnection();

        User createUser = userJdbcApiRepository.create(
                connection,
                request.getName(),
                request.getAge(),
                request.getJob(),
                request.getSpecialty());
        Message createMessage = messageJdbcApiRepository.create(connection, createUser.getId(), createUser.getName());

        //collection 반환을 해야 한다.
        connection.close();
        return UserResponseDto.from(createUser, Collections.singletonList(createMessage));
    }

}
