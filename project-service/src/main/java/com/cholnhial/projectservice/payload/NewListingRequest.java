package com.cholnhial.projectservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NewListingRequest {

    private Long artefactId;

    private String name;

    private String description;

    private BigDecimal price;

    private String picture;

    private String email;
}
