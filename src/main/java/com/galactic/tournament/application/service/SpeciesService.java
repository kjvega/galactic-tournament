package com.galactic.tournament.application.service;

import com.galactic.tournament.application.port.in.RegisterSpeciesUseCase;
import com.galactic.tournament.application.port.in.GetAllSpeciesUseCase;
import com.galactic.tournament.application.port.in.GetRankingUseCase;
import com.galactic.tournament.application.port.in.FightSpeciesUseCase;
import com.galactic.tournament.application.port.out.SpeciesRepository;
import com.galactic.tournament.application.port.out.BattleResultRepository;
import com.galactic.tournament.domain.exception.*;
import com.galactic.tournament.domain.model.BattleResult;
import com.galactic.tournament.domain.model.Species;
import com.galactic.tournament.infrastructure.entity.BattleResultEntity;
import com.galactic.tournament.infrastructure.entity.SpeciesEntity;
import com.galactic.tournament.infrastructure.mapper.BattleResultMapper;
import com.galactic.tournament.infrastructure.mapper.SpeciesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpeciesService implements
        RegisterSpeciesUseCase,
        GetAllSpeciesUseCase,
        GetRankingUseCase,
        FightSpeciesUseCase {

    private final SpeciesRepository speciesRepository;
    private final BattleResultRepository battleResultRepository;

    public SpeciesService(SpeciesRepository speciesRepository, BattleResultRepository battleResultRepository) {
        this.speciesRepository = speciesRepository;
        this.battleResultRepository = battleResultRepository;
    }

    @Override
    public Species register(Species species) {
        SpeciesEntity entity = SpeciesMapper.toEntity(species);

        speciesRepository.findByName(entity.getName())
                .ifPresent(e -> { throw new SpeciesAlreadyExistsException(entity.getName()); });

        SpeciesEntity saved = speciesRepository.save(entity);
        return SpeciesMapper.toDomain(saved);
    }

    @Override
    public List<Species> getAll() {
        return speciesRepository.findAll()
                .stream()
                .map(SpeciesMapper::toDomain)
                .toList();
    }

    @Override
    public List<Species> getRanking() {
        return speciesRepository.findAllOrderedByVictories()
                .stream()
                .map(SpeciesMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Species fight(Long firstSpeciesId, Long secondSpeciesId) {
        SpeciesEntity first = speciesRepository.findById(firstSpeciesId)
                .orElseThrow(() -> new SpeciesNotFoundException(firstSpeciesId));
        SpeciesEntity second = speciesRepository.findById(secondSpeciesId)
                .orElseThrow(() -> new SpeciesNotFoundException(secondSpeciesId));

        SpeciesEntity winner = determineWinner(first, second);

        try {
            winner.setVictories(winner.getVictories() + 1);
            speciesRepository.save(winner);

            BattleResult result = new BattleResult(
                    winner.getId(),
                    (winner.equals(first)) ? second.getId() : first.getId(),
                    winner.getName(),
                    (winner.equals(first)) ? second.getName() : first.getName(),
                    "WINNER"
            );

            BattleResultEntity resultEntity = BattleResultMapper.toEntity(result);
            battleResultRepository.save(resultEntity);

            return SpeciesMapper.toDomain(winner);

        } catch (Exception ex) {
            throw new BattlePersistenceException("Failed to persist battle result. Transaction rolled back.", ex);
        }
    }

    private SpeciesEntity determineWinner(SpeciesEntity first, SpeciesEntity second) {
        if (first.getPowerLevel() > second.getPowerLevel()) {
            return first;
        } else if (first.getPowerLevel() < second.getPowerLevel()) {
            return second;
        }

        int comparison = first.getName().compareToIgnoreCase(second.getName());
        if (comparison == 0) {
            throw new TotalTieException(first.getName());
        }

        return (comparison < 0) ? first : second;
    }
}
