package com.cholnhial.shopservice.payload;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class ShopResponse {

    private Long id;

    private Long artefactId;

    private String name;

    private String description;

    private BigDecimal price;

    private String picture;

    private String email;
}
