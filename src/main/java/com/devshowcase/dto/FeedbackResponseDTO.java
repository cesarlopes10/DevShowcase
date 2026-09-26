package com.devshowcase.dto;

public class FeedbackResponseDTO {

    private Long id;
    private Integer rating;
    private String comment;
    private Long projectId;
    private Double averageRating;

    public FeedbackResponseDTO(Long id, Integer rating, String comment,
                               Long projectId, Double averageRating) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.projectId = projectId;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Double getAverageRating() {
        return averageRating;
    }
}