package net.project.controller;

import java.io.IOException;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.project.dao.*;
import net.project.model.*;
/**
 * Servlet implementation class RegisterServlet
 */

@WebServlet ("/registeration")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 request.getRequestDispatcher("jsp/success.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userType = request.getParameter("userType");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUserType(userType);

        boolean isRegistered = false;

        if (userType.equals("student")) {
            isRegistered = userDAO.createStudent(newUser);
        } else {
            isRegistered = userDAO.createProfessor(newUser);
        }

        if (isRegistered) {
            request.setAttribute("successMessage", "Registration complete. Please log in.");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
        }
        request.getRequestDispatcher("jsp/Registration_message.jsp").forward(request, response);
    }
}
