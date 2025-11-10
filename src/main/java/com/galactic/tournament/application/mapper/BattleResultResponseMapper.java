package com.galactic.tournament.application.mapper;

import com.galactic.tournament.domain.model.BattleResult;
import com.galactic.tournament.application.dto.BattleResultResponse;

import java.util.List;

public class BattleResultResponseMapper {

    private BattleResultResponseMapper() {}

    public static BattleResultResponse toResponse(BattleResult result) {
        return new BattleResultResponse(
                result.getId(),
                result.getWinnerName(),
                result.getLoserName(),
                result.getResult(),
                result.getCreatedAt()
        );
    }

    public static List<BattleResultResponse> toResponseList(List<BattleResult> results) {
        return results.stream()
                .map(BattleResultResponseMapper::toResponse)
                .toList();
    }
}
