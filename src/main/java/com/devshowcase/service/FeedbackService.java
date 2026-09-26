package com.devshowcase.service;

import com.devshowcase.dto.FeedbackRequestDTO;
import com.devshowcase.dto.FeedbackResponseDTO;
import com.devshowcase.entity.Feedback;
import com.devshowcase.entity.Project;
import com.devshowcase.repository.FeedbackRepository;
import com.devshowcase.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           ProjectRepository projectRepository) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public FeedbackResponseDTO create(Long projectId, FeedbackRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Feedback feedback = new Feedback();
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setProject(project);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        List<Feedback> feedbacks = feedbackRepository.findAll()
                .stream()
                .filter(f -> f.getProject().getId().equals(projectId))
                .toList();

        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(average);
        projectRepository.save(project);

        return new FeedbackResponseDTO( 
                savedFeedback.getId(),
                savedFeedback.getRating(),
                savedFeedback.getComment(),
                project.getId(),
                project.getAverageRating()
        );
    }
}