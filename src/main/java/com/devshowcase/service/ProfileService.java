package com.devshowcase.service;

import com.devshowcase.dto.ProfileRequestDTO;
import com.devshowcase.dto.ProfileResponseDTO;
import com.devshowcase.entity.Profile;
import com.devshowcase.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDTO create(ProfileRequestDTO dto) {

        Profile profile = new Profile();

        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setBio(dto.getBio());

        Profile savedProfile = profileRepository.save(profile);

        return toResponseDTO(savedProfile);
    }

    public ProfileResponseDTO findById(Long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Perfil não encontrado"));

        return toResponseDTO(profile);
    }

    private ProfileResponseDTO toResponseDTO(Profile profile) {

        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getEmail(),
                profile.getBio()
        );
    }
}