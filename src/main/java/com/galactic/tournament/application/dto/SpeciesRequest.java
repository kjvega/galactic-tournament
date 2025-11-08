package com.galactic.tournament.application.dto;

import lombok.Getter;

@Getter
public class SpeciesRequest {
    private String name;
    private int powerLevel;
    private String specialSkill;

    public SpeciesRequest() {}

    public SpeciesRequest(String name, int powerLevel, String specialSkill) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.specialSkill = specialSkill;
    }

}
