package com.cholnhial.projectservice.payload;

import com.cholnhial.projectservice.model.Artefact;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
public class ProjectResponse {

    private Long id;

    private Long userId;

    private ArtefactResponse artefact;

    private String title;

    private Boolean isComplete;

    private String description;
}
