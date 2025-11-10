package com.galactic.tournament.application.port.out;

import com.galactic.tournament.infrastructure.entity.BattleResultEntity;
import com.galactic.tournament.infrastructure.entity.SpeciesEntity;

import java.util.List;

public interface BattleResultRepository {
    BattleResultEntity save(BattleResultEntity battleResult);
    List<BattleResultEntity> findAll();
}
