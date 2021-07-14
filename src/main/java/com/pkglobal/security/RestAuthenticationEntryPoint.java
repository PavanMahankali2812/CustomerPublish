package com.pkglobal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	/*
	 * @Override public void commence(HttpServletRequest request,
	 * HttpServletResponse response, AuthenticationException authException) throws
	 * IOException, ServletException {
	 * 
	 * ErrorResponse errorResponse = new ErrorResponse();
	 * errorResponse.setStatus(PublisherConstants.ERROR.getValue());
	 * errorResponse.setMessage(authException.getLocalizedMessage());
	 * errorResponse.setErrorType("Unauthorized");
	 * logger.error("ErrorResponse : {}", errorResponse); final ObjectMapper mapper
	 * = new ObjectMapper(); mapper.writeValue(response.getOutputStream(),
	 * errorResponse);
	 * 
	 * }
	 */
}