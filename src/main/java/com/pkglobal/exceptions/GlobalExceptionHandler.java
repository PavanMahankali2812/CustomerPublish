package com.pkglobal.exceptions;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.prokarma.producer.constants.PublisherConstants;
import com.prokarma.producer.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(PublisherConstants.ERROR.getValue());
        errorResponse.setMessage(errors);
        errorResponse.setErrorType(ex.getClass().getName());
        logger.error("ErrorResponse :{}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFound(NoHandlerFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(PublisherConstants.ERROR.getValue());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorType(ex.getClass().getName());
        logger.error("ErrorResponse :{}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedClientException.class)
    public ResponseEntity<ErrorResponse> unauthorizedHandler(UnauthorizedClientException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(PublisherConstants.ERROR.getValue());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorType(ex.getClass().getName());
        logger.error("ErrorResponse :{}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomerPublishServiceExpection.class)
    public final ResponseEntity<ErrorResponse> handleException(CustomerPublishServiceExpection ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(PublisherConstants.ERROR.getValue());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorType(ex.getClass().getName());
        logger.error("ErrorResponse :{}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
