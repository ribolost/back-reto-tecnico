package web.exception;

import common.error.ElementNotFoundException;
import common.error.GenericException;
import common.error.TypeableException;
import common.error.ElementValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(TypeableException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getCustomExceptionType(),
                exception.getExceptionMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException constraintException) {
        ElementValidationException exception = new ElementValidationException("There's a format error.",
                mapConstraintViolations(constraintException.getConstraintViolations()));

        return new ResponseEntity<>(new ExceptionResponse(exception.getCustomExceptionType(),
                exception.getExceptionMessage(), exception.getDetailedErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleBodyConstraintViolationException(MethodArgumentNotValidException bodyException) {
        ElementValidationException exception = new ElementValidationException("There's a format error.",
                mapConstraintViolations(bodyException.getFieldErrors()));
        return new ResponseEntity<>(new ExceptionResponse(exception.getCustomExceptionType(),
                exception.getExceptionMessage(), exception.getDetailedErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({GenericException.class})
    public ResponseEntity<ExceptionResponse> handleGenericException(TypeableException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getCustomExceptionType(),
                exception.getExceptionMessage(), exception.getDetailedErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> mapConstraintViolations(Set<ConstraintViolation<?>> violations) {
        Map<String, String> constraintErrors = new HashMap<>();
        violations.forEach(violation ->
                constraintErrors.put(violation.getPropertyPath().toString(), violation.getMessageTemplate())
        );
        return constraintErrors;
    }

    private Map<String, String> mapConstraintViolations(List<FieldError> violations) {
        Map<String, String> constraintErrors = new HashMap<>();
        violations.forEach(violation ->
                constraintErrors.put(violation.getField(), violation.getDefaultMessage())
        );

        return constraintErrors;
    }

}
