package com.galactic.tournament.domain;

import com.galactic.tournament.domain.exception.TotalTieException;
import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.domain.service.impl.BattleRulesService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleRulesTest {

    private final BattleRulesService battleRules = new BattleRulesService();

    @Test
    void shouldWinSpeciesWithHigherPowerLevel() {
        Species s1 = new Species("Zorgon", 80, "Fire Blast");
        Species s2 = new Species("Krypton", 50, "Water Slash");

        Species winner = battleRules.determineWinner(s1, s2);

        assertEquals("Zorgon", winner.getName());
    }

    @Test
    void shouldWinAlphabeticallyWhenPowerIsEqual() {
        Species s1 = new Species("Alpha", 70, "Laser");
        Species s2 = new Species("Beta", 70, "Shield");

        Species winner = battleRules.determineWinner(s1, s2);

        assertEquals("Alpha", winner.getName());
    }

    @Test
    void shouldThrowTotalTieExceptionWhenNamesAndPowerAreEqual() {
        Species s1 = new Species("Alpha", 70, "Laser");
        Species s2 = new Species("Alpha", 70, "Shield");

        assertThrows(TotalTieException.class, () -> battleRules.determineWinner(s1, s2));
    }
}
