package com.galactic.tournament.application.dto;

import java.time.LocalDateTime;

public record BattleResultResponse(
        Long id,
        String winnerName,
        String loserName,
        String result,
        LocalDateTime createdAt
) {}
