package com.devshowcase.dto;

import java.util.Set;

public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private Long profileId;
    private Set<Long> technologyIds;

    public ProjectResponseDTO(
            Long id,
            String title,
            String description,
            String url,
            Long profileId,
            Set<Long> technologyIds) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.profileId = profileId;
        this.technologyIds = technologyIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Set<Long> getTechnologyIds() {
        return technologyIds;
    }
}