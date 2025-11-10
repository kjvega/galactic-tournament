package com.galactic.tournament.infrastructure.controller;

import com.galactic.tournament.application.dto.BattleResultResponse;
import com.galactic.tournament.application.dto.SpeciesResponse;
import com.galactic.tournament.application.port.in.*;
import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.application.dto.SpeciesRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final RegisterSpeciesUseCase registerUseCase;
    private final GetAllSpeciesUseCase listUseCase;
    private final GetRankingUseCase rankingUseCase;
    private final FightSpeciesUseCase fightUseCase;
    private final GetBattleResultsUseCase getBattleResultsUseCase;


    public SpeciesController(RegisterSpeciesUseCase registerUseCase,
                             GetAllSpeciesUseCase listUseCase,
                             GetRankingUseCase rankingUseCase,
                             FightSpeciesUseCase fightUseCase, GetBattleResultsUseCase getBattleResultsUseCase) {
        this.registerUseCase = registerUseCase;
        this.listUseCase = listUseCase;
        this.rankingUseCase = rankingUseCase;
        this.fightUseCase = fightUseCase;
        this.getBattleResultsUseCase = getBattleResultsUseCase;
    }

    @GetMapping
    public List<SpeciesResponse> getAll() {
        return listUseCase.getAll();
    }

    @GetMapping("/battles")
    public List<BattleResultResponse> getBattleResults() {
        return getBattleResultsUseCase.getBattleResults();
    }


    @GetMapping("/ranking")
    public List<SpeciesResponse> getRanking() {
        return rankingUseCase.getRanking();
    }

    @PostMapping
    public SpeciesResponse register(@RequestBody SpeciesRequest speciesRequest) {
        return registerUseCase.register(speciesRequest);
    }


    @PostMapping("/fight")
    public SpeciesResponse fight(@RequestParam Long firstId, @RequestParam Long secondId) {
        return fightUseCase.fight(firstId, secondId);
    }
}
