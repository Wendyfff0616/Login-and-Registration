package com.uniquedeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
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
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        RequestDispatcher dispatcher = null;
        Connection con = null;

//        PrintWriter out = response.getWriter();
//        out.print(uname);
//        out.print(uemail);
//        out.print(upwd);
//        out.print(umobile);

        try {
            Class.forName("com.mysql.jdbc.Driver"); // enables the application to recognize the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube?useSSL=false","root","root");
            PreparedStatement pst = con.prepareStatement("insert into users(uname, upwd, uemail, umobile) values(?,?,?,?)");
            pst.setString(1, uname);
            pst.setString(2, upwd);
            pst.setString(3, uemail);
            pst.setString(4, umobile);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("registration.jsp");

            if (rowCount > 0) {
                request.setAttribute("status", "success"); // database insertion successful
            } else {
                request.setAttribute("status", "failed");
            }

            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
