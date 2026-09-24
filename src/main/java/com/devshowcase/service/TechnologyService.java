package com.devshowcase.service;

import com.devshowcase.dto.TechnologyRequestDTO;
import com.devshowcase.dto.TechnologyResponseDTO;
import com.devshowcase.entity.Technology;
import com.devshowcase.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    public TechnologyService(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    public TechnologyResponseDTO create(TechnologyRequestDTO dto) {

        Technology technology = new Technology();
        technology.setName(dto.getName());

        Technology savedTechnology =
                technologyRepository.save(technology);

        return toResponseDTO(savedTechnology);
    }

    public List<TechnologyResponseDTO> findAll() {

        return technologyRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private TechnologyResponseDTO toResponseDTO(Technology technology) {

        return new TechnologyResponseDTO(
                technology.getId(),
                technology.getName()
        );
    }
}