package com.sbrf.reboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ask")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }

        session.setAttribute("visitCounter", visitCounter);
        String userName = req.getParameter("name");
        resp.setContentType("text/html");
        try {
            PrintWriter printWriter = resp.getWriter();
            if (userName == null) {
                printWriter.write("Привет, неизвестный человек!<br>");
            } else {
                printWriter.write("Привет, " + userName + "!<br>");
            }
            printWriter.write("Обращений к адресу: " + visitCounter);
        } catch (IOException e) {
            System.out.println("Не удалось прочитать значение из resp.");
            e.printStackTrace();
        }
    }
}
