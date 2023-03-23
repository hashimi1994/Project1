package net.project.controller;

import java.io.IOException;
import net.project.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.project.dao.*;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet(name = "LoginServlet2", urlPatterns = {"/LoginServlet2"})
public class LoginServlet2 extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	  public LoginServlet2() {
	        super();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        UserDAO userDAO = new UserDAO();
	        User user = userDAO.login(username, password);

	        if (user != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);

	            if ("student".equals(user.getUserType())) {
	                response.sendRedirect("/student_dashboard.jsp");
	            } else if ("professor".equals(user.getUserType())) {
	                response.sendRedirect("/professor_dashboard.jsp");
	            } else {
	                request.setAttribute("errorMessage", "Invalid user type");
	                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
	            }
	        } else {
	            request.setAttribute("errorMessage", "Invalid username or password");
	            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
	        }
	    }
	}