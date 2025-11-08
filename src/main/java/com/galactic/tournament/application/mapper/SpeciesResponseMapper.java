package com.galactic.tournament.application.mapper;

import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.application.dto.SpeciesResponse;

import java.util.List;

public class SpeciesResponseMapper {

    private SpeciesResponseMapper() {}

    public static SpeciesResponse toResponse(Species species) {
        return new SpeciesResponse(
                species.getId(),
                species.getName(),
                species.getPowerLevel(),
                species.getSpecialSkill(),
                species.getVictories()
        );
    }

    public static List<SpeciesResponse> toResponseList(List<Species> speciesList) {
        return speciesList.stream()
                .map(SpeciesResponseMapper::toResponse)
                .toList();
    }
}
