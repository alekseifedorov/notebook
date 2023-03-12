package my.assignment.controller;

import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import my.assignment.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
    public static final String ERROR = "Error: ";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError(EntityNotFoundException e) {
        log.error(ERROR, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleError(ValidationException e) {
        log.error(ERROR, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception e) {
        log.error(ERROR, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
