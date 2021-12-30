package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dao.Request;
import com.sbrf.reboot.dao.Response;

import java.io.Serializable;

public class JSONUtils {
    public static String toJSON(Serializable serializableClass) {
        String jsonString = "";
        try {
            jsonString = new ObjectMapper().writeValueAsString(serializableClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static Request JSONtoRequest(String jsonString) {
        Request request = new Request();
        try {
            request = new ObjectMapper().readValue(jsonString, Request.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static Response JSONtoResponse(String jsonString) {
        Response response = new Response();
        try {
            response = new ObjectMapper().readValue(jsonString, Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
