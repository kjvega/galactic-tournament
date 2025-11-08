package com.galactic.tournament.application.dto;

import lombok.Getter;

@Getter
public class SpeciesResponse {
    private final Long id;
    private final String name;
    private final int powerLevel;
    private final String specialSkill;
    private final int victories;

    public SpeciesResponse(Long id, String name, int powerLevel, String specialSkill, int victories) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.specialSkill = specialSkill;
        this.victories = victories;
    }

}
