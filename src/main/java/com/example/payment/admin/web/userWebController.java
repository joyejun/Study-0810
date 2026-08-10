package com.example.payment.admin.web;

import com.example.payment.Repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class userWebController {
    private final UserRepository userRepository;
}
