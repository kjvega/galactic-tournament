package com.galactic.tournament.infrastructure.controller;

import com.galactic.tournament.application.dto.SpeciesRequest;
import com.galactic.tournament.application.dto.SpeciesResponse;
import com.galactic.tournament.application.port.in.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeciesControllerTest {

    @InjectMocks
    private SpeciesController controller;

    @Mock
    private RegisterSpeciesUseCase registerUseCase;

    @Mock
    private GetAllSpeciesUseCase listUseCase;

    @Mock
    private GetRankingUseCase rankingUseCase;

    @Mock
    private FightSpeciesUseCase fightUseCase;

    private SpeciesResponse response;
    private SpeciesRequest request;

    @BeforeEach
    void setUp() {
        request = new SpeciesRequest("Zorgon", 80, "Fire Blast");
        response = new SpeciesResponse(1L, "Zorgon", 80, "Fire Blast", 0);
    }

    @Test
    void shouldRegisterSpeciesSuccessfully() {
        when(registerUseCase.register(any(SpeciesRequest.class))).thenReturn(response);

        SpeciesResponse result = controller.register(request);

        assertEquals("Zorgon", result.getName());
        assertEquals(80, result.getPowerLevel());
    }

    @Test
    void shouldReturnAllSpecies() {
        when(listUseCase.getAll()).thenReturn(List.of(response));

        List<SpeciesResponse> result = controller.getAll();

        assertEquals(1, result.size());
        assertEquals("Zorgon", result.get(0).getName());
    }

    @Test
    void shouldReturnRanking() {
        when(rankingUseCase.getRanking()).thenReturn(List.of(response));

        List<SpeciesResponse> result = controller.getRanking();

        assertEquals("Zorgon", result.get(0).getName());
    }

    @Test
    void shouldReturnWinnerWhenFighting() {
        when(fightUseCase.fight(1L, 2L)).thenReturn(response);

        SpeciesResponse result = controller.fight(1L, 2L);

        assertEquals("Zorgon", result.getName());
    }
}
