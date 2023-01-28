package com.andes.metamon.exception;

import com.andes.metamon.exception.badRequest.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundUser.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundException(final NotFoundUser e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotMatchPassword.class)
    public ResponseEntity<BaseExceptionResponse> handleNotMatchPasswordException(final NotMatchPassword e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(DuplicateEmail.class)
    public ResponseEntity<BaseExceptionResponse> handleDuplicateEmailException(final DuplicateEmail e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(InvalidTokenForm.class)
    public ResponseEntity<BaseExceptionResponse> handleInvalidTokenFormException(final InvalidTokenForm e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
    @ExceptionHandler(NotFoundTokenFromHeader.class)
    public ResponseEntity<BaseExceptionResponse> handleNotFoundTokenFromHeaderException(final NotFoundTokenFromHeader e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseExceptionResponse.of(e));
    }
}
