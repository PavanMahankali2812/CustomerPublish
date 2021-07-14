package com.pkglobal.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;

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
		logger.error("ErrorResponse : {}", authException);
		resolver.resolveException(request, response, null, authException);
	}

}