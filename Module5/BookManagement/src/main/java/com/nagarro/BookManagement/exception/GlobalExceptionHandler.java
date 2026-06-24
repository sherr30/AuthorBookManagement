package com.nagarro.BookManagement.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> errorBody(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("timestamp", Instant.now().toString());
        return body;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, Object> body = errorBody(HttpStatus.BAD_REQUEST, "Validation failed");
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        body.put("fieldErrors", fieldErrors);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorNotFound(
            AuthorNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorBody(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(
            BookNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorBody(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateAuthorException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateAuthor(
            DuplicateAuthorException ex) {

        return ResponseEntity.badRequest()
                .body(errorBody(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorBody(HttpStatus.CONFLICT,
                        "Cannot delete author because the author is linked with one or more books."));
    }
}