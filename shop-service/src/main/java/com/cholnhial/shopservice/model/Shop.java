package com.cholnhial.shopservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long artefactId;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private String picture;
}
