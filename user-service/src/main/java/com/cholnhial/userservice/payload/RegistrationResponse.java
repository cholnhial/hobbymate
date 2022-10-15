package com.cholnhial.userservice.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistrationResponse {
    private Long id;

    private String fullName;

    private String email;

    private String mobile;

    private String suburb;

    private String bio;
}
