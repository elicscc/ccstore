package com.ccstay.ccstore.util;

import com.ccstay.ccstore.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T toObject(String json, Class<T> c) {
        T o = null;
        try {
            o = mapper.readValue(json, c);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return o;

    }

    public static String toJson(Object o) {

        String json = null;
        try {
            json = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return json;
    }
}
