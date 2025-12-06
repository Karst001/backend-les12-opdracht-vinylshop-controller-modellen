package nl.novi.vinylshop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    //global exception handler that parses the error at @Valid in the controllers
    //Only need @Valid right before @RequestBody like 'createGenre(@Valid @RequestBody GenreRequestDTO genreInput)'
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        //instantiate a map
        Map<String, String> errors = new HashMap<>();

        //iterate through all errors
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        //return nicely formatted error
        return ResponseEntity.badRequest().body(errors);
    }
}
