package com.cholnhial.projectservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
public class Artefact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column
    private BigDecimal price;

    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isListed;

    @Column
    private String picture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artefact artefact = (Artefact) o;
        return id != null && Objects.equals(id, artefact.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
