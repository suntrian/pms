package org.sunt.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sunt.common.mvc.R;
import org.sunt.exception.NotExistsException;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExistsException.class)
    public ResponseEntity<R<?>> handleNotExistsException(NotExistsException ex) {
        return new ResponseEntity<>(R.failure(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<R<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(R.failure(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<R<?>> handleNullPointException(NullPointerException ex) {
        return new ResponseEntity<>(R.failure(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<R<?>> handleGeneralException(Exception ex) {
        log.error("UNKNOWN EXCEPTION", ex);
        return new ResponseEntity<>(R.failure(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
