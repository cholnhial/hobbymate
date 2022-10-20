package com.cholnhial.projectservice.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String fullName;

    private String email;

    private String mobile;

    private String suburb;

    private String bio;
}
