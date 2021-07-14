package com.pkglobal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		/*
		 * ErrorResponse errorResponse = new ErrorResponse();
		 * errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		 * errorResponse.setMessage(authException.getLocalizedMessage());
		 * errorResponse.setErrorType("Unauthorized");
		 * logger.error("ErrorResponse : {}", errorResponse); final ObjectMapper mapper
		 * = new ObjectMapper(); mapper.writeValue(response.getOutputStream(),
		 * errorResponse);
		 */

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
	}

}