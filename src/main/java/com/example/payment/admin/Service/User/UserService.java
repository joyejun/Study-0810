package com.example.payment.admin.Service.User;

import com.example.payment.Repository.user.User;
import com.example.payment.Repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Integer id) {
        Optional<User> wrappedUser = userRepository.findById(id);
                User        user = wrappedUser
                        .orElseThrow(() -> new RuntimeException("찾으시는 유저가 없습니다."+ id));
        return user;
    }

    public Optional<User> findUser(Integer id) {
        return userRepository.findById(id);
    }
}
