package com.galactic.tournament.application.port.in;

import com.galactic.tournament.application.dto.SpeciesResponse;

public interface FightSpeciesUseCase {

    SpeciesResponse fight(Long firstSpeciesId, Long secondSpeciesId);
}
