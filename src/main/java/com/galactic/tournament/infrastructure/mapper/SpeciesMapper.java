package com.galactic.tournament.infrastructure.mapper;

import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.infrastructure.entity.SpeciesEntity;

public class SpeciesMapper {

    public static SpeciesEntity toEntity(Species domain) {
        return new SpeciesEntity(
                domain.getId(),
                domain.getName(),
                domain.getPowerLevel(),
                domain.getSpecialSkill(),
                domain.getVictories()
        );
    }

    public static Species toDomain(SpeciesEntity entity) {
        return new Species(
                entity.getId(),
                entity.getName(),
                entity.getPowerLevel(),
                entity.getSpecialSkill(),
                entity.getVictories()
        );
    }
}
