package com.sprint.mission.study0902.controller;

import com.sprint.mission.study0902.controller.dto.UserResponseDto;
import com.sprint.mission.study0902.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserResponseDto retrieve(@PathVariable @NonNull Integer id) throws SQLException {
        return null;
    }
}
