package com.pkglobal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkglobal.exceptions.ProducerServiceException;

public class ObjectMapperUtil {

	static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtil.class);

	private ObjectMapperUtil() {
		throw new AssertionError("No Object Creation");
	}

	public static String returnJsonFromObject(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			throw new ProducerServiceException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					ex.getMessage());
		}
	}
}
