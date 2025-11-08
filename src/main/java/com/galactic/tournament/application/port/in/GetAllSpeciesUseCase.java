package com.galactic.tournament.application.port.in;

import com.galactic.tournament.application.dto.SpeciesResponse;
import java.util.List;

public interface GetAllSpeciesUseCase {
    List<SpeciesResponse> getAll();
}
