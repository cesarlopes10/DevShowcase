package com.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequestDTO {

    @NotBlank(message = "O nome da tecnologia é obrigatório")
    private String name;

    public TechnologyRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}