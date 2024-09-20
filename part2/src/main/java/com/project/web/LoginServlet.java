package com.project.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import db.DatabaseManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String userRole = DatabaseManager.authenticateUser(username, password);

        if (userRole != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userRole", userRole);
            response.sendRedirect("dashboard.jsp"); // Redirect to the dashboard if login is successful
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response); // Forward back to the login page with an error message
        }
    }
}