package com.spring.jwt.spring_security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
public class HelperUtils {
    public static final ObjectWriter JSON_WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();
}
