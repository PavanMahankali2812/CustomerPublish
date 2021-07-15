package com.pkglobal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		/*
		 * ErrorResponse errorResponse = new ErrorResponse();
		 * errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		 * errorResponse.setMessage(authException.getLocalizedMessage());
		 * errorResponse.setErrorType(authException.getClass().getSimpleName());
		 * logger.error("ErrorResponse : {}", errorResponse); final ObjectMapper mapper
		 * = new ObjectMapper(); mapper.writeValue(response.getOutputStream(),
		 * errorResponse);
		 */

		if (authException instanceof InsufficientAuthenticationException) {

			if (authException.getCause() instanceof InvalidTokenException) {
				response.getOutputStream().println("{ " + "\"message\": \"Token has expired\","
						+ "\"type\": \"Unauthorized\"," + "\"status\": 401" + "}");
			}
		}
		if (authException instanceof AuthenticationCredentialsNotFoundException) {

			response.getOutputStream().println("{ " + "\"message\": \"Missing Authorization Header\","
					+ "\"type\": \"Unauthorized\"," + "\"status\": 401" + "}");
		}

	}

}