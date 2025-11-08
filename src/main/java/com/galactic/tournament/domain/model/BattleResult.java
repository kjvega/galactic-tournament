package com.galactic.tournament.domain.model;

import java.time.LocalDateTime;

public class BattleResult {
    private Long id;
    private Long winnerId;
    private Long loserId;
    private String winnerName;
    private String loserName;
    private String result;
    private LocalDateTime createdAt;

    public BattleResult(Long id, Long winnerId, Long loserId, String winnerName, String loserName, String result, LocalDateTime createdAt) {
        this.id = id;
        this.winnerId = winnerId;
        this.loserId = loserId;
        this.winnerName = winnerName;
        this.loserName = loserName;
        this.result = result;
        this.createdAt = createdAt;
    }

    public BattleResult(Long winnerId, Long loserId, String winnerName, String loserName, String result) {
        this(null, winnerId, loserId, winnerName, loserName, result, LocalDateTime.now());
    }


    public Long getId() { return id; }
    public Long getWinnerId() { return winnerId; }
    public Long getLoserId() { return loserId; }
    public String getWinnerName() { return winnerName; }
    public String getLoserName() { return loserName; }
    public String getResult() { return result; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
