package com.galactic.tournament.domain.service;

import com.galactic.tournament.domain.model.Species;

public interface BattleRules {
    Species determineWinner(Species first, Species second);
}
