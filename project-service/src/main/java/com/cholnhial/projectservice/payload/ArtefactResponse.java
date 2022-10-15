package com.cholnhial.projectservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ArtefactResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isListed;

    private String picture;
}
