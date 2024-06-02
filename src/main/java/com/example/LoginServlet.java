package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("admin", new User("admin", "password"));
        users.put("user", new User("user", "password"));
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