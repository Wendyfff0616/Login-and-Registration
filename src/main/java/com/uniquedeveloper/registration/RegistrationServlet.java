package com.uniquedeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register") // Servlet URL mapping
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; // Serial version UID for ensuring compatibility during serialization

    /**
     * Handles HTTP POST requests
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
    }
}
