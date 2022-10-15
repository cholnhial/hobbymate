package com.cholnhial.userservice.payload;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistrationRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    private String mobile;

    @NotBlank
    private String suburb;

    private String bio;
}
