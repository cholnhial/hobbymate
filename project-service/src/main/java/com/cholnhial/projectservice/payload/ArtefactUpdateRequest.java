package com.cholnhial.projectservice.payload;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class ArtefactUpdateRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private BigDecimal price;

    private Boolean isListed;

    @NotNull
    private String picture;
}
