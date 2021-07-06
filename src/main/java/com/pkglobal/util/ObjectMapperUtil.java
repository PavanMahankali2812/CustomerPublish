package com.pkglobal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ObjectMapperUtil {

    static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtil.class);

    private ObjectMapperUtil() {
        throw new AssertionError("No Object Creation");
    }

    public static String returnJsonFromObject(Object object) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new Exception();
        }
    }
}
