package com.galactic.tournament.application.port.in;

import com.galactic.tournament.domain.model.Species;
import java.util.List;

public interface GetRankingUseCase {
    List<Species> getRanking();
}
