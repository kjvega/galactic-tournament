package com.galactic.tournament.application.service;

import com.galactic.tournament.application.dto.BattleResultResponse;
import com.galactic.tournament.application.dto.SpeciesResponse;
import com.galactic.tournament.application.mapper.BattleResultResponseMapper;
import com.galactic.tournament.application.mapper.SpeciesResponseMapper;
import com.galactic.tournament.application.port.in.*;
import com.galactic.tournament.application.port.out.*;
import com.galactic.tournament.domain.exception.*;
import com.galactic.tournament.domain.model.*;
import com.galactic.tournament.domain.service.BattleRules;
import com.galactic.tournament.application.dto.SpeciesRequest;
import com.galactic.tournament.infrastructure.entity.*;
import com.galactic.tournament.infrastructure.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpeciesService implements
        RegisterSpeciesUseCase,
        GetAllSpeciesUseCase,
        GetRankingUseCase,
        FightSpeciesUseCase,
        GetBattleResultsUseCase
{

    private final SpeciesRepository speciesRepository;
    private final BattleResultRepository battleResultRepository;
    private final BattleRules battleRules;

    public SpeciesService(SpeciesRepository speciesRepository,
                          BattleResultRepository battleResultRepository, BattleRules battleRules) {
        this.speciesRepository = speciesRepository;
        this.battleResultRepository = battleResultRepository;
        this.battleRules = battleRules;
    }

    @Override
    public List<SpeciesResponse> getAll() {
        List<Species> domainListSpecies = speciesRepository.findAll()
                .stream()
                .map(SpeciesMapper::toDomain)
                .toList();
        return SpeciesResponseMapper.toResponseList(domainListSpecies);
    }
    @Override
    public List<BattleResultResponse> getBattleResults() {
        List<BattleResult> domainListBattleResult = battleResultRepository.findAll()
                .stream()
                .map(BattleMapper::toDomain)
                .toList();
        return BattleResultResponseMapper.toResponseList(domainListBattleResult);
    }

    @Override
    public List<SpeciesResponse> getRanking() {
        List<Species> domainListSpecies = speciesRepository.findAllOrderedByVictories()
                .stream()
                .map(SpeciesMapper::toDomain)
                .toList();
        return  SpeciesResponseMapper.toResponseList(domainListSpecies);
    }

    @Override
    public SpeciesResponse register(SpeciesRequest speciesRequest) {
        Species species = new Species(
                speciesRequest.getName(),
                speciesRequest.getPowerLevel(),
                speciesRequest.getSpecialSkill()
        );
        SpeciesEntity entity = SpeciesMapper.toEntity(species);
        speciesRepository.findByName(entity.getName())
                .ifPresent(e -> { throw new SpeciesAlreadyExistsException(entity.getName()); });
        SpeciesEntity saved = speciesRepository.save(entity);
        Species domainSpecies = SpeciesMapper.toDomain(saved);
        return SpeciesResponseMapper.toResponse(domainSpecies);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpeciesResponse fight(Long firstSpeciesId, Long secondSpeciesId) {
        SpeciesEntity firstEntity = speciesRepository.findById(firstSpeciesId)
                .orElseThrow(() -> new SpeciesNotFoundException(firstSpeciesId));
        SpeciesEntity secondEntity = speciesRepository.findById(secondSpeciesId)
                .orElseThrow(() -> new SpeciesNotFoundException(secondSpeciesId));

        Species firstDomain = SpeciesMapper.toDomain(firstEntity);
        Species secondDomain = SpeciesMapper.toDomain(secondEntity);

        Species winnerDomain = battleRules.determineWinner(firstDomain, secondDomain);

        try {
            SpeciesEntity winnerEntity = SpeciesMapper.toEntity(winnerDomain);
            winnerEntity.setId(winnerDomain.getId());
            winnerEntity.setVictories(winnerEntity.getVictories() + 1);
            speciesRepository.save(winnerEntity);

            BattleResult result = new BattleResult(
                    winnerEntity.getId(),
                    (winnerEntity.getId().equals(firstEntity.getId())) ? secondEntity.getId() : firstEntity.getId(),
                    winnerEntity.getName(),
                    (winnerEntity.getId().equals(firstEntity.getId())) ? secondEntity.getName() : firstEntity.getName(),
                    "WINNER"
            );
            battleResultRepository.save(BattleMapper.toEntity(result));
             Species domainSpecies = SpeciesMapper.toDomain(winnerEntity);
            return SpeciesResponseMapper.toResponse(domainSpecies);

        } catch (Exception ex) {
            throw new BattlePersistenceException("Failed to persist battle result. Transaction rolled back.", ex);
        }
    }
}
