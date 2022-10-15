package com.cholnhial.userservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private String suburb;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String bio;
}
