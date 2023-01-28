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
//
//    @ExceptionHandler(InvalidValueException.class)
//    public ResponseEntity<ExceptionResponse> handleInvalidValueException(final InvalidValueException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), e.getErrorCode().getValue(), e.getMessage());
//        return ResponseEntity.badRequest().body(ExceptionResponse.from(e));
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ExceptionResponse> handleInvalidValueException(final DataIntegrityViolationException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), REQUEST_DUPLICATED.getValue(), e.getMessage());
//
//        return ResponseEntity.badRequest().body(ExceptionResponse.from(REQUEST_DUPLICATED_MESSAGE, REQUEST_DUPLICATED));
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), INVALID_REQUEST_BODY_TYPE.getValue(), e.getMessage());
//        return ResponseEntity.badRequest()
//                .body(ExceptionResponse.from(REQUEST_DATA_FORMAT_ERROR_MESSAGE, INVALID_REQUEST_BODY_TYPE));
//    }
//
//    @ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class})
//    public ResponseEntity<ExceptionResponse> handleInvalidQueryParameterException(Exception e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), INVALID_SEARCH_PARAM.getValue(), e.getMessage());
//        return ResponseEntity.badRequest()
//                .body(ExceptionResponse.from(REQUEST_DATA_FORMAT_ERROR_MESSAGE, INVALID_SEARCH_PARAM));
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> handleValidationException(final MethodArgumentNotValidException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), INVALID_REQUEST_BODY_TYPE.getValue(), e.getMessage());
//        final StringBuilder stringBuilder = new StringBuilder();
//        e.getBindingResult().getAllErrors().forEach((error) -> stringBuilder.append(error.getDefaultMessage())
//                .append(System.lineSeparator()));
//        return ResponseEntity.badRequest()
//                .body(ExceptionResponse.from(stringBuilder.toString(), INVALID_REQUEST_BODY_TYPE));
//    }
//
//    @ExceptionHandler({RefreshTokenNotFoundException.class, DuplicatedRefreshTokenSavedException.class,
//            RefreshTokenExpiredException.class, TooManyRefreshTokenAffectedException.class})
//    public ResponseEntity<ExceptionResponse> handleRefreshTokenException(final UnauthorizedException e,
//                                                                         final HttpServletRequest request) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), e.getErrorCode().getValue(), e.getMessage());
//        ExceptionResponse responseBody = ExceptionResponse.from(e);
//        final Cookie cookie = WebUtils.getCookie(request, REFRESH_TOKEN);
//        if (cookie != null) {
//            ResponseCookie responseCookie = refreshTokenCookieProvider.createLogoutCookie();
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
//                    .body(responseBody);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(responseBody);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ExceptionResponse> handleUnauthorizedException(final UnauthorizedException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), e.getErrorCode().getValue(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.from(e));
//    }
//
//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity<ExceptionResponse> handleForbiddenMemberException(final ForbiddenException e) {
//        log.info(LOG_FORMAT, e.getClass().getSimpleName(), e.getErrorCode().getValue(), e.getMessage());
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExceptionResponse.from(e));
//    }
//
//    @ExceptionHandler({ExternalServerException.class, InternalServerException.class})
//    public ResponseEntity<ExceptionResponse> handleInternalException(final CustomException e) {
//        return ResponseEntity.internalServerError().body(ExceptionResponse.from(e));
//    }
//
//    @ExceptionHandler(UriTooLongException.class)
//    public ResponseEntity<ExceptionResponse> handleUriTooLongException(final CustomException e) {
//        return ResponseEntity.status(HttpStatus.URI_TOO_LONG).body(ExceptionResponse.from(e));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleUnhandledException(final Exception e) {
//        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), INTERNAL_SERVER_ERROR.getValue(), e.getMessage());
//        return ResponseEntity.internalServerError()
//                .body(ExceptionResponse.from(INTERNAL_SERVER_ERROR_MESSAGE, INTERNAL_SERVER_ERROR));
//    }
//}
