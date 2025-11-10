package com.galactic.tournament.infrastructure.exception;

import com.galactic.tournament.domain.exception.BattlePersistenceException;
import com.galactic.tournament.domain.exception.SpeciesAlreadyExistsException;
import com.galactic.tournament.domain.exception.SpeciesNotFoundException;
import com.galactic.tournament.domain.exception.TotalTieException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpeciesAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExists(SpeciesAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("type", "ALREADY_EXISTS");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(SpeciesNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("type", "NOT_FOUND");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TotalTieException.class)
    public ResponseEntity<Map<String, String>> handleTotalTie(TotalTieException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("type", "TOTAL_TIE");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(BattlePersistenceException.class)
    public ResponseEntity<Map<String, String>> handleBattlePersistence(BattlePersistenceException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("type", "BATTLE_PERSISTENCE_ERROR");
        error.put("message", "Ocurrió un error inesperado al guardar la batalla: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
