package com.galactic.tournament.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "species")
public class SpeciesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int powerLevel;
    private String specialSkill;
    private int victories;

    public SpeciesEntity() {}

    public SpeciesEntity(Long id, String name, int powerLevel, String specialSkill, int victories) {
        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.specialSkill = specialSkill;
        this.victories = victories;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPowerLevel() { return powerLevel; }
    public String getSpecialSkill() { return specialSkill; }
    public int getVictories() { return victories; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }
    public void setSpecialSkill(String specialSkill) { this.specialSkill = specialSkill; }
    public void setVictories(int victories) { this.victories = victories; }
}
