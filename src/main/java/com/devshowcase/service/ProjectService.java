package com.devshowcase.service;

import com.devshowcase.dto.ProjectRequestDTO;
import com.devshowcase.dto.ProjectResponseDTO;
import com.devshowcase.entity.Profile;
import com.devshowcase.entity.Project;
import com.devshowcase.entity.Technology;
import com.devshowcase.repository.ProfileRepository;
import com.devshowcase.repository.ProjectRepository;
import com.devshowcase.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto) {

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() ->
                        new RuntimeException("Perfil não encontrado"));

        Set<Technology> technologies = new HashSet<>();

        if (dto.getTechnologyIds() != null) {
            technologies.addAll(
                    technologyRepository.findAllById(dto.getTechnologyIds())
            );
        }

        Project project = new Project();

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setUrl(dto.getUrl());
        project.setProfile(profile);
        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return toResponseDTO(savedProject);
    }

    public List<ProjectResponseDTO> findAll() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    public Page<ProjectResponseDTO> findAll(
            String technology,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Project> projects;

        if (technology != null && !technology.isBlank()) {
            projects = projectRepository
                    .findByTechnologiesNameIgnoreCase(technology, pageable);
        } else {
            projects = projectRepository.findAll(pageable);
        }

        return projects.map(this::toResponseDTO);
    }

    private ProjectResponseDTO toResponseDTO(Project project) {

        Set<Long> technologyIds = project.getTechnologies()
                .stream()
                .map(Technology::getId)
                .collect(Collectors.toSet());

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getUrl(),
                project.getProfile().getId(),
                technologyIds,
                project.getAverageRating(),
                project.getUpvotes()
        );
    }
    public ProjectResponseDTO upvote(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        if (project.getUpvotes() == null) {
            project.setUpvotes(0);
        }

        project.setUpvotes(project.getUpvotes() + 1);

        Project savedProject = projectRepository.save(project);

        return toResponseDTO(savedProject);
    }
}