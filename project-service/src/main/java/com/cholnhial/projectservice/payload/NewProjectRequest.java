package com.cholnhial.projectservice.payload;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewProjectRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    private String description;
}
