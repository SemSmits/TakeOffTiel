package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("admin", new User("admin", "password", "email", "admin"));
        users.put("user", new User("user", "password", "email", "user"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = users.get(username);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (user != null && user.getPassword().equals(password)) {
            out.write("Login successful");
        } else {
            out.write("Invalid credentials");
        }
    }


}
