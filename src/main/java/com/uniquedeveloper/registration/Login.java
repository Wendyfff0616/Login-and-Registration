package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests for the Login servlet.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uemail = request.getParameter("email");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        Connection con = null;

//        System.out.println("Received username: " + uemail);
//        System.out.println("Received password: " + upwd);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // enables the application to recognize the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube?useSSL=false","root","root");
            PreparedStatement pst = con.prepareStatement("select * from users where uemail = ? and upwd = ?");

            pst.setString(1, uemail);
            pst.setString(2, upwd);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
//                System.out.println("Login successful for user: " + rs.getString("uemail"));
                session.setAttribute("email", rs.getString("uemail"));
                session.setAttribute("name", rs.getString("uname"));
                response.sendRedirect("index.jsp");
                return;
            } else {
//                System.out.println("Login failed - no matching user found");
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
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
