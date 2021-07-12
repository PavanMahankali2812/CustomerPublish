package com.pkglobal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.pkglobal.constant.PublisherConstants;
import com.pkglobal.model.ErrorResponse;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

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