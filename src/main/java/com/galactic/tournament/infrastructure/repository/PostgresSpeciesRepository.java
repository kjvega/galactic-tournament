package com.galactic.tournament.infrastructure.repository;

import com.galactic.tournament.application.port.out.SpeciesRepository;
import com.galactic.tournament.infrastructure.entity.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostgresSpeciesRepository extends SpeciesRepository, JpaRepository<SpeciesEntity, Long> {

    Optional<SpeciesEntity> findByName(String name);

    @Query("SELECT s FROM SpeciesEntity s ORDER BY s.victories DESC, s.powerLevel DESC")
    List<SpeciesEntity> findAllOrderedByVictories();
}
