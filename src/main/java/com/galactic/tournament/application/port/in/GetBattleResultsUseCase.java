package com.galactic.tournament.application.port.in;

import com.galactic.tournament.application.dto.BattleResultResponse;

import java.util.List;

public interface GetBattleResultsUseCase {
    List<BattleResultResponse> getBattleResults();
}

