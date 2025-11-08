package com.galactic.tournament.domain.exception;

public class SpeciesAlreadyExistsException extends RuntimeException {

    public SpeciesAlreadyExistsException(String name) {
        super("Species '" + name + "' already exists in the tournament.");
    }
}
