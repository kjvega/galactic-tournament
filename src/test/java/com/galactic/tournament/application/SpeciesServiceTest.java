package com.galactic.tournament.application;

import com.galactic.tournament.application.dto.SpeciesRequest;
import com.galactic.tournament.application.dto.SpeciesResponse;
import com.galactic.tournament.application.service.SpeciesService;
import com.galactic.tournament.application.port.out.BattleResultRepository;
import com.galactic.tournament.application.port.out.SpeciesRepository;
import com.galactic.tournament.domain.exception.SpeciesAlreadyExistsException;
import com.galactic.tournament.domain.service.BattleRules;
import com.galactic.tournament.infrastructure.entity.SpeciesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpeciesServiceTest {

    @Mock
    private SpeciesRepository speciesRepository;

    @Mock
    private BattleResultRepository battleResultRepository;

    @Mock
    private BattleRules battleRules;

    @InjectMocks
    private SpeciesService speciesService;

    private SpeciesRequest request;
    private SpeciesEntity entity;

    @BeforeEach
    void setUp() {
        request = new SpeciesRequest("Zorgon", 80, "Fire Blast");
        entity = new SpeciesEntity();
        entity.setId(1L);
        entity.setName("Zorgon");
        entity.setPowerLevel(80);
        entity.setSpecialSkill("Fire Blast");
        entity.setVictories(0);
    }

    @Test
    void shouldRegisterNewSpeciesSuccessfully() {
        when(speciesRepository.findByName("Zorgon")).thenReturn(Optional.empty());
        when(speciesRepository.save(any(SpeciesEntity.class))).thenReturn(entity);

        SpeciesResponse response = speciesService.register(request);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Zorgon");
        verify(speciesRepository).save(any(SpeciesEntity.class));
    }

    @Test
    void shouldThrowExceptionWhenSpeciesAlreadyExists() {
        when(speciesRepository.findByName("Zorgon")).thenReturn(Optional.of(entity));

        assertThatThrownBy(() -> speciesService.register(request))
                .isInstanceOf(SpeciesAlreadyExistsException.class)
                .hasMessageContaining("Zorgon");

        verify(speciesRepository, never()).save(any());
    }

    @Test
    void shouldReturnAllSpecies() {
        when(speciesRepository.findAll()).thenReturn(List.of(entity));

        List<SpeciesResponse> result = speciesService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Zorgon");
        verify(speciesRepository).findAll();
    }

    @Test
    void shouldReturnRankingOrderedByVictories() {
        when(speciesRepository.findAllOrderedByVictories()).thenReturn(List.of(entity));

        List<SpeciesResponse> ranking = speciesService.getRanking();

        assertThat(ranking).hasSize(1);
        assertThat(ranking.get(0).getName()).isEqualTo("Zorgon");
        verify(speciesRepository).findAllOrderedByVictories();
    }
}
