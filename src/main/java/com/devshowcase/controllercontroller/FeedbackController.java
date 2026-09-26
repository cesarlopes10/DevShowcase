package com.devshowcase.controller;

import com.devshowcase.dto.FeedbackRequestDTO;
import com.devshowcase.dto.FeedbackResponseDTO;
import com.devshowcase.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> createFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO dto) {

        FeedbackResponseDTO response = feedbackService.create(id, dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}