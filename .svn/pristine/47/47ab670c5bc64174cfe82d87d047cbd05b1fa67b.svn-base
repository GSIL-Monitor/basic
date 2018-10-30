package com.wgb.util.excel.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
    	objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    	objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }
    
    
    public static String toJson(Object object){
        StringWriter writer=new StringWriter();
        try {
            objectMapper.writeValue(writer,object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public static <T> T parse(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> T parseList(String json, TypeReference<T> type){
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
