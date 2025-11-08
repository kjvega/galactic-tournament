package com.galactic.tournament.domain.exception;

public class TotalTieException extends RuntimeException {

    public TotalTieException(String speciesName) {
        super("The battle between identical species '" + speciesName + "' ended in a total tie.");
    }
}
