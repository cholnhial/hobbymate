package com.cholnhial.projectservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewProjectResponse {

    private Long id;
    private String title;
    private String description;
    private String shortDescription;
}
