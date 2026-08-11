package com.example.payment.Controller.admin.api;


import com.example.payment.Application.User.UserAdminApplcation;
import com.example.payment.Common.context.UserContext;
import com.example.payment.Controller.dto.UserAdminCreateRequestDto;
import com.example.payment.Controller.dto.UserAdminResponseDto;
import com.example.payment.Controller.dto.UserAdminUpdateRequestDto;
import com.example.payment.Repository.user.User;
import com.example.payment.Repository.user.UserRepository;
import com.example.payment.internal.api.dto.RequestingUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserAdminApplcation userAdminApplcation;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/api/users")
    public List<UserAdminResponseDto> retrieve() {
        return userAdminApplcation.retrieve();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/api/users/{id}")
    public UserAdminResponseDto retrieve(@PathVariable Integer id) {
        return userAdminApplcation.retrieve(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/api/users")
    public UserAdminResponseDto create(@RequestBody UserAdminCreateRequestDto requestDto) {
        User creating = requestDto.to();
        return userAdminApplcation.create(creating);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/api/users/{id}")
    public UserAdminResponseDto update(@PathVariable Integer id, @RequestBody UserAdminUpdateRequestDto request) {
        Integer requestedUserId = request.getRequestUserId();
        try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
            return userAdminApplcation.update(id, request);
        }
    }
    @RequestMapping(method = RequestMethod.PATCH, value = "/admin/api/users/{id}/active")
    public void active(@PathVariable Integer id, @RequestBody RequestingUserDto request) {
        Integer requestedUserId = request.getRequestUserId();
        try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
            userAdminApplcation.active(id);
        }
    }
    @RequestMapping(method = RequestMethod.PATCH, value = "/admin/api/users/{id}/soft-delete")
    public void softDelete(@PathVariable Integer id, @RequestBody RequestingUserDto request) {
        Integer requestedUserId = request.getRequestUserId();
        try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
            userAdminApplcation.softDelete(id);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/api/users/{id}/hard-delete")
    public void hardDelete(@PathVariable Integer id, @RequestBody RequestingUserDto request) {
        Integer requestedUserId = request.getRequestUserId();
        try (UserContext.ContextScope ignored = UserContext.withUser(requestedUserId)) {
            userAdminApplcation.hardDelete(id);
        }
    }

}
