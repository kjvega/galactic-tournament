package com.galactic.tournament.infrastructure.mapper;

import com.galactic.tournament.domain.model.BattleResult;
import com.galactic.tournament.infrastructure.entity.BattleResultEntity;

public class BattleMapper {

    public static BattleResultEntity toEntity(BattleResult battleResult) {
        return new BattleResultEntity(
                battleResult.getWinnerId(),
                battleResult.getLoserId(),
                battleResult.getWinnerName(),
                battleResult.getLoserName(),
                battleResult.getResult()
        );
    }

    public static BattleResult toDomain(BattleResultEntity entity) {
        return new BattleResult(
                entity.getId(),
                entity.getWinnerId(),
                entity.getLoserId(),
                entity.getWinnerName(),
                entity.getLoserName(),
                entity.getResult(),
                entity.getCreatedAt()
        );
    }
}
