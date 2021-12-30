package com.sbrf.reboot.utils;

import com.sbrf.reboot.dao.Request;
import com.sbrf.reboot.dao.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JSONUtilsTest {

    @Test
    void toJSONRequest() {
        Request request = new Request("ATM12345");
        Assertions.assertTrue(JSONUtils.toJSON(request).contains("atmNumber"));
    }

    @Test
    void toJSONResponse() {
        Response response = new Response("SUCCESS");
        Assertions.assertTrue(JSONUtils.toJSON(response).contains("statusCode"));
    }

    @Test
    void JSONtoRequest() {
        Request request = JSONUtils.JSONtoRequest("{\"atmNumber\":\"ATM12345\"}");
        Assertions.assertEquals("ATM12345", request.getAtmNumber());
    }

    @Test
    void JSONtoResponse() {
        Response request = JSONUtils.JSONtoResponse("{\"statusCode\":\"SUCCESS\"}");
        Assertions.assertEquals("SUCCESS", request.getStatusCode());
    }
}