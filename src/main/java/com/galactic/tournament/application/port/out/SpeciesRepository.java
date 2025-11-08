package com.galactic.tournament.application.port.out;

import com.galactic.tournament.infrastructure.entity.SpeciesEntity;
import java.util.List;
import java.util.Optional;

public interface SpeciesRepository {
    SpeciesEntity save(SpeciesEntity entity);
    Optional<SpeciesEntity> findByName(String name);
    Optional<SpeciesEntity> findById(Long id);
    List<SpeciesEntity> findAll();
    List<SpeciesEntity> findAllOrderedByVictories();
}
