package com.galactic.tournament.domain.service.impl;

import com.galactic.tournament.domain.exception.TotalTieException;
import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.domain.service.BattleRules;
import org.springframework.stereotype.Component;

@Component
public class BattleRulesService implements BattleRules {

    @Override
    public Species determineWinner(Species first, Species second) {
        if (first.getPowerLevel() > second.getPowerLevel()) return first;
        if (second.getPowerLevel() > first.getPowerLevel()) return second;

        int comparison = first.getName().compareToIgnoreCase(second.getName());
        if (comparison == 0) {
            throw new TotalTieException(first.getName());
        }
        return (comparison < 0) ? first : second;
    }
}
