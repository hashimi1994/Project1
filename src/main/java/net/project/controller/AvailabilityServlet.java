package net.project.controller;

import jakarta.servlet.ServletException;
import java.io.IOException;


import java.sql.Timestamp;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.project.model.*;
import net.project.dao.*;
/**
 * Servlet implementation class AvailabilityServlet
 */
@WebServlet(name = "AvailabilityServlet", urlPatterns = {"/AvailabilityServlet"})
public class AvailabilityServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        AvailabilityDAO availabilityDAO = new AvailabilityDAO();
	        List<TimeSlot> timeSlots = availabilityDAO.getAllTimeSlots();

	        HttpSession session = request.getSession();
	        session.setAttribute("timeSlots", timeSlots);

	        response.sendRedirect("professor_dashboard.jsp");
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("create".equals(action)) {
	            // Handle creating a new time slot
	        } else if ("update".equals(action)) {
	            // Handle updating an existing time slot
	        } else if ("delete".equals(action)) {
	            // Handle deleting an existing time slot
	        } else {
	            // Handle invalid action
	            response.sendRedirect("schedule_dashboard.jsp?error=invalid-action");
	        }
	    }
		}
       

