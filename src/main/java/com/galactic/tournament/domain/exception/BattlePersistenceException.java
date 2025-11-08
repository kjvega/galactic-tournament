package com.galactic.tournament.domain.exception;

public class BattlePersistenceException extends RuntimeException {

    public BattlePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BattlePersistenceException(String message) {
        super(message);
    }
}
