package com.galactic.tournament.application.port.in;

import com.galactic.tournament.application.dto.SpeciesResponse;
import com.galactic.tournament.application.dto.SpeciesRequest;

public interface RegisterSpeciesUseCase {
    SpeciesResponse register(SpeciesRequest species);
}
