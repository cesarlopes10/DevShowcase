package com.devshowcase.repository;

import com.devshowcase.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByTechnologiesNameIgnoreCase(
            String technology,
            Pageable pageable
    );
}