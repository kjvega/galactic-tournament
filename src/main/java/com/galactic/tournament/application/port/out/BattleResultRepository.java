package com.galactic.tournament.application.port.out;

import com.galactic.tournament.infrastructure.entity.BattleResultEntity;

public interface BattleResultRepository {
    BattleResultEntity save(BattleResultEntity battleResult);
}
