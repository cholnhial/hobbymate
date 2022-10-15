package com.cholnhial.userservice.controller;

import com.cholnhial.userservice.mapper.UserMapper;
import com.cholnhial.userservice.model.User;
import com.cholnhial.userservice.payload.RegistrationRequest;
import com.cholnhial.userservice.payload.RegistrationResponse;
import com.cholnhial.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserMapper userMapper;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {
        var newUser = userMapper.RegistrationRequestToUser(request);
        newUser = this.userService.saveNewUser(newUser);
        return ResponseEntity.ok().body(userMapper.userToRegistrationResponse(newUser));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RegistrationResponse> getByUserId(@PathVariable("userId") Long userId) {
        Optional<User> userOptional = this.userService.getOneById(userId);
        return userOptional.map(u -> ResponseEntity.ok().body(userMapper.userToRegistrationResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<RegistrationResponse> login(@PathVariable("email") String email) {
        Optional<User> userOptional = this.userService.getOneByEmail(email);
        return userOptional.map(u -> ResponseEntity.ok().body(userMapper.userToRegistrationResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
