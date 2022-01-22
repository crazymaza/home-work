package com.sbrf.reboot.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/ask")
public class HelloServlet extends HttpServlet {
    private final AtomicInteger visitCounter = new AtomicInteger(1);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Integer counter = (Integer) session.getAttribute("visitCounter");
        if (counter != 0) {
            visitCounter.getAndIncrement();
        }

        session.setAttribute("visitCounter", visitCounter.get());
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
