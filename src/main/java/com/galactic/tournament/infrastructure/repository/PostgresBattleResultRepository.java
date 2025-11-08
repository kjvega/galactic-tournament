package com.galactic.tournament.infrastructure.repository;

import com.galactic.tournament.application.port.out.BattleResultRepository;
import com.galactic.tournament.infrastructure.entity.BattleResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresBattleResultRepository extends BattleResultRepository, JpaRepository<BattleResultEntity, Long> {
}
