package com.pkglobal.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import com.pkglobal.constant.PublisherConstants;
import com.pkglobal.model.ErrorResponse;

@Component
public class RestExceptionTranslationFilter extends ExceptionTranslationFilter {
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	public RestExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationEntryPoint);

	}

	@Override
	protected void sendStartAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain,
			AuthenticationException reason) throws ServletException, IOException {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(PublisherConstants.ERROR.getValue());
		errorResponse.setMessage(reason.getLocalizedMessage());
		errorResponse.setErrorType("Unauthorized");
		logger.error("ErrorResponse : {}", errorResponse);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), errorResponse);

		super.sendStartAuthentication(req, resp, chain, reason);
	}
}
