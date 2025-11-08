package com.galactic.tournament.application.port.in;

import com.galactic.tournament.domain.model.Species;

public interface FightSpeciesUseCase {

    Species fight(Long firstSpeciesId, Long secondSpeciesId);
}
