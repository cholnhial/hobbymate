package com.cholnhial.projectservice.payload;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProjectUpdateRequest {
    @NotNull
    private Long id;

    @NotNull
    private ArtefactUpdateRequest artefact;

    @NotBlank
    private String title;

    @NotBlank
    private String status;

    private String description;
}
