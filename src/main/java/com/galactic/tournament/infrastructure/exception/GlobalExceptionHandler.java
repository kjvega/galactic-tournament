package com.galactic.tournament.infrastructure.exception;

import com.galactic.tournament.domain.exception.BattlePersistenceException;
import com.galactic.tournament.domain.exception.SpeciesAlreadyExistsException;
import com.galactic.tournament.domain.exception.SpeciesNotFoundException;
import com.galactic.tournament.domain.exception.TotalTieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpeciesAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(SpeciesAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<String> handleNotFound(SpeciesNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TotalTieException.class)
    public ResponseEntity<String> handleTotalTie(TotalTieException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ExceptionHandler(BattlePersistenceException.class)
    public ResponseEntity<String> handleBattlePersistence(BattlePersistenceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred while saving the battle: " + ex.getMessage());
    }

}
