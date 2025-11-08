package com.galactic.tournament.domain.exception;

public class SpeciesNotFoundException extends RuntimeException {

    public SpeciesNotFoundException(Long id) {
        super("Species with ID '" + id + "' was not found in the tournament.");
    }
}
