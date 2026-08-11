package com.example.payment.Controller.admin.web;

import com.example.payment.Application.User.UserAdminApplcation;
import com.example.payment.Controller.dto.User.UserAdminResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserWebController {
    private final UserAdminApplcation userAdminApplication;


    @RequestMapping(method = RequestMethod.GET, value = "/admin/web/users")
    public String users(Model model) {
        List<UserAdminResponseDto> users = userAdminApplication.retrieve();
        model.addAttribute("users", users);
        return "/users/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/web/users/{id}")
    public String user(@RequestParam Integer id, Model model) {
        UserAdminResponseDto user = userAdminApplication.retrieve(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("grade", user.getGrade());
        model.addAttribute("point", user.getPoint());
        model.addAttribute("deleted", user.isDeleted());
        return "/users/detail";
    }


}
