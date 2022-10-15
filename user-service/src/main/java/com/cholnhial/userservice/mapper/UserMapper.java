package com.cholnhial.userservice.mapper;

import com.cholnhial.userservice.model.User;
import com.cholnhial.userservice.payload.RegistrationRequest;
import com.cholnhial.userservice.payload.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User RegistrationRequestToUser(RegistrationRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setSuburb(request.getSuburb());
        user.setBio(request.getBio());

        return user;
    }

    public RegistrationResponse userToRegistrationResponse(User user) {
        RegistrationResponse response = new RegistrationResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setMobile(user.getMobile());
        response.setBio(user.getBio());
        response.setSuburb(user.getSuburb());

        return response;
    }
}
