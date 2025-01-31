package org.springbootapp.proplus_backendapplication.exception;

import jakarta.validation.ConstraintViolationException;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<MessageDto> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageDto(ex.getMessage()));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<MessageDto> handleNotAuthorizedException(NotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDto(ex.getMessage()));
    }

    @ExceptionHandler({UsernameNotFoundException.class, NotFoundException.class})
    public ResponseEntity<MessageDto> handleUsernameNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDto> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> Optional.ofNullable(error.getDefaultMessage()).orElse("Invalid field value"))));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<MessageDto>> handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getConstraintViolations().stream()
                .map(cv -> new MessageDto(cv.getMessage()))
                .collect(Collectors.toSet()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<MessageDto> handleDataAccessException(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDto(ex.getMessage()));
    }
}
