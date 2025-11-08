package com.galactic.tournament.application.port.in;

import com.galactic.tournament.domain.model.Species;

public interface RegisterSpeciesUseCase {
    Species register(Species species);
}
