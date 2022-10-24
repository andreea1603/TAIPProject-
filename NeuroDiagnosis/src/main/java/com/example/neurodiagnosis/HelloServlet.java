package com.example.neurodiagnosis;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
            Connection conn = null;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SchedulerProblem", "postgres", "admin");
                if (conn != null) {
                    out.println("Super");
                } else {
                    out.println("Not Super!");
                }
                out.println("am iesit");
            } catch (Exception e) {
                out.println(e);
            }
    }

    public void destroy() {
    }
}