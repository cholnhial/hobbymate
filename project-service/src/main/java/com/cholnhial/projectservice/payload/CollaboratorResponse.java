package com.cholnhial.projectservice.payload;

import com.cholnhial.projectservice.model.Project;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
public class CollaboratorResponse {
    private Long id;

    private String fullName;

    private String email;

    private String mobile;

    private String suburb;

    private String bio;

    private Long userId;

}
