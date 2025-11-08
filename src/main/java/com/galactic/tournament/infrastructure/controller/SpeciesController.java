package com.galactic.tournament.infrastructure.controller;

import com.galactic.tournament.application.port.in.*;
import com.galactic.tournament.domain.model.Species;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final RegisterSpeciesUseCase registerUseCase;
    private final GetAllSpeciesUseCase listUseCase;
    private final GetRankingUseCase rankingUseCase;
    private final FightSpeciesUseCase fightUseCase;

    public SpeciesController(RegisterSpeciesUseCase registerUseCase,
                             GetAllSpeciesUseCase listUseCase,
                             GetRankingUseCase rankingUseCase,
                             FightSpeciesUseCase fightUseCase) {
        this.registerUseCase = registerUseCase;
        this.listUseCase = listUseCase;
        this.rankingUseCase = rankingUseCase;
        this.fightUseCase = fightUseCase;
    }

    @PostMapping
    public Species register(@RequestBody Species species) {
        return registerUseCase.register(species);
    }

    @GetMapping
    public List<Species> getAll() {
        return listUseCase.getAll();
    }

    @GetMapping("/ranking")
    public List<Species> getRanking() {
        return rankingUseCase.getRanking();
    }

    @PostMapping("/fight")
    public Species fight(@RequestParam Long firstId, @RequestParam Long secondId) {
        return fightUseCase.fight(firstId, secondId);
    }
}
