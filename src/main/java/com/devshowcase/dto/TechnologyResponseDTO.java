package com.devshowcase.dto;

public class TechnologyResponseDTO {

    private Long id;
    private String name;

    public TechnologyResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}