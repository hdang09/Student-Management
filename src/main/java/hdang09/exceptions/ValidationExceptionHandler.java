package hdang09.exceptions;

import hdang09.enums.ResponseStatus;
import hdang09.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;

@ControllerAdvice
@RestController
public class ValidationExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response<BindException>> handleValidationException(BindException ex) {
        // Get all errors
        List<FieldError> fieldErrors = ex.getFieldErrors();

        // Get the first error message
        FieldError firstError = fieldErrors.get(0);
        String errorMessage = firstError.getDefaultMessage();

        Response<BindException> response = new Response<>(ResponseStatus.ERROR, errorMessage, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}