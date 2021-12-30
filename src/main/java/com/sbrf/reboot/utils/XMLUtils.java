package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dao.Request;
import com.sbrf.reboot.dao.Response;

import java.io.Serializable;

public class XMLUtils {
    public static String toXML(Serializable serializableClass) {
        String xmlString = "";
        try {
            xmlString = new XmlMapper().writeValueAsString(serializableClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public static Request XMLtoRequest(String xmlString) {
        Request request = new Request();
        try {
            request = new XmlMapper().readValue(xmlString, Request.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static Response XMLtoResponse(String xmlString) {
        Response response = new Response();
        try {
            response = new XmlMapper().readValue(xmlString, Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
