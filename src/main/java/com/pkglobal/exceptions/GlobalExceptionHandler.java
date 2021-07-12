package com.pkglobal.exceptions;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.pkglobal.constant.PublisherConstants;
import com.pkglobal.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		String errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(","));
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(errors);
		errorResponse.setErrorType(ex.getClass().getName());
		logger.error("ErrorResponse :{}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> noHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorType(ex.getClass().getName());
		logger.error("ErrorResponse :{}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ UnauthorizedClientException.class, AuthenticationException.class })
	public ResponseEntity<ErrorResponse> unauthorizedHandler(Exception ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorType(ex.getClass().getName());
		logger.error("ErrorResponse :{}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ ResourceAccessException.class, ApplicationRuntimeException.class, Exception.class })
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorType(ex.getClass().getName());
		logger.error("ErrorResponse :{}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MissingRequestHeaderException.class,
			HttpRequestMethodNotSupportedException.class, })
	public final ResponseEntity<ErrorResponse> requestValidationException(Exception ex, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(ex.getLocalizedMessage());
		errorResponse.setErrorType(ex.getClass().getSimpleName());
		logger.error("ErrorResponse : {}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		if (authException instanceof InsufficientAuthenticationException) {

			if (authException.getCause() instanceof InvalidTokenException) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setStatus(PublisherConstants.ERROR.getValue());
				errorResponse.setMessage(authException.getLocalizedMessage());
				errorResponse.setErrorType("Unauthorized");
				logger.error("ErrorResponse : {}", errorResponse);
				final ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), errorResponse);

			}
		}
	}

}
