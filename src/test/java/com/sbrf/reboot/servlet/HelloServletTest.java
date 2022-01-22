package com.sbrf.reboot.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HelloServletTest {
    private StringWriter stringWriter;

    HttpServletRequest request = mock(HttpServletRequest.class); //Почему-то не работает через @Mock
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession httpSession = mock(HttpSession.class);

    @BeforeEach
    public void mockedRequestAnswer() throws IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getSession().getAttribute("visitCounter")).thenReturn(0);

        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        writer.flush();
    }

    @Test
    public void doGetWithoutParametersOneTimeTest() {
        new HelloServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("name");
        Assertions.assertEquals("Привет, неизвестный человек!<br>Обращений к адресу: 1", stringWriter.toString());
    }

    @Test
    public void doGetWithoutParametersTwoTimeTest() throws IOException {
        new HelloServlet().doGet(request, response);

        StringWriter stringWriter2 = new StringWriter();
        PrintWriter writer2 = new PrintWriter(stringWriter2);

        when(response.getWriter()).thenReturn(writer2);
        when(request.getSession().getAttribute("visitCounter")).thenReturn(1);

        new HelloServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("name");
        writer2.flush();

        Assertions.assertEquals("Привет, неизвестный человек!<br>Обращений к адресу: 2", stringWriter2.toString());
    }

    @Test
    public void doGetWithParameterNameTest() {
        when(request.getParameter("name")).thenReturn("MyName");

        new HelloServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("name");
        Assertions.assertEquals("Привет, MyName!<br>Обращений к адресу: 1", stringWriter.toString());
    }
}
