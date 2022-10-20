package com.cholnhial.userservice.service;

import com.cholnhial.userservice.model.User;
import com.cholnhial.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User saveNewUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> getOneById(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> getOneByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
