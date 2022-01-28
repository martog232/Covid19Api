package app.controller.handler;

import app.controller.error.APIError;
import app.controller.error.APIErrorCode;
import app.controller.exception.MethodArgumentNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private static final String STACK_TRACE_LOG = "Stack trace: ";

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<APIError> handleException(EntityNotFoundException ex) {
        LOGGER.error(STACK_TRACE_LOG, ex);

        APIErrorCode code = APIErrorCode.ENTITY_NOT_FOUND;

        return ResponseEntity.status(404).body(new APIError(code.getCode(), code.getMessage(), code.getDescription()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<APIError> handleException(MethodArgumentNotValidException ex) {
        LOGGER.error(STACK_TRACE_LOG, ex);

        APIErrorCode code = APIErrorCode.METHOD_ARG_NOT_VALID;

        return ResponseEntity.status(400).body(new APIError(code.getCode(), code.getMessage(), code.getDescription()));
    }
}
