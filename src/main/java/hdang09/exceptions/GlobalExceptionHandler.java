package hdang09.exceptions;

import hdang09.enums.ResponseStatus;
import hdang09.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Exception>> handleException(Exception ex) {
        Response<Exception> response = new Response<>(ResponseStatus.ERROR, ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
