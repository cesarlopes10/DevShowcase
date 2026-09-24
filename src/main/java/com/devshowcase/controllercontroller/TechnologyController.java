package com.devshowcase.controller;

import com.devshowcase.dto.TechnologyRequestDTO;
import com.devshowcase.dto.TechnologyResponseDTO;
import com.devshowcase.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> create(
            @Valid @RequestBody TechnologyRequestDTO dto) {

        TechnologyResponseDTO response =
                technologyService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> findAll() {

        return ResponseEntity.ok(technologyService.findAll());
    }
}