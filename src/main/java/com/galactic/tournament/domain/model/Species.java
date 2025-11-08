package com.galactic.tournament.domain.model;

public class Species {

    private final Long id;
    private final String name;
    private final int powerLevel;
    private final String specialSkill;
    private final int victories;

    public Species(Long id, String name, int powerLevel, String specialSkill, int victories) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.specialSkill = specialSkill;
        this.victories = victories;
    }


    public Species(String name, int powerLevel, String specialSkill) {
        this(null, name, powerLevel, specialSkill, 0);
    }

    public Species addVictory() {
        return new Species(id, name, powerLevel, specialSkill, victories + 1);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPowerLevel() { return powerLevel; }
    public String getSpecialSkill() { return specialSkill; }
    public int getVictories() { return victories; }
}
