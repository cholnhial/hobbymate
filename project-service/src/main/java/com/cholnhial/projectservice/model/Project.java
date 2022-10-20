package com.cholnhial.projectservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "artefact_id", referencedColumnName = "id")
    private Artefact artefact;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<Collaborator> collaborators = new HashSet<>();

    @Column
    private String title;

    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isComplete;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column
    private String shortDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
