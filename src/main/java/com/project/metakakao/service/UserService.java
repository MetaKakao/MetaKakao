package com.project.metakakao.service;

import com.project.metakakao.entity.User;
import com.project.metakakao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUser(String username) {
        return userRepository.getUser(username);
    }
}
