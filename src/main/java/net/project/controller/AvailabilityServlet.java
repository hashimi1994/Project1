package net.project.controller;
import java.time.LocalDateTime;


import jakarta.servlet.RequestDispatcher;
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
@WebServlet("/professoravailability")
public class AvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private AvailabilityDAO availabilityDAO;

    public void init() {
        availabilityDAO = new AvailabilityDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("submit".equals(action)) {
            LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
            int professorId = Integer.parseInt(request.getParameter("professorId"));
            boolean claimed = "on".equals(request.getParameter("claimed"));

            TimeSlot timeSlot = new TimeSlot(0, startTime, endTime, professorId, claimed);
            
            if (availabilityDAO.createTimeSlot(timeSlot)) {
                // Redirect to the success page or display a success message
                response.sendRedirect("jsp/timeslotsuccess.jsp"); // Replace with the success page URL
            }
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
	            TimeSlot timeslot = availabilityDAO.getTimeSlotById(id);

	            if (timeslot != null && availabilityDAO.deleteTimeSlot(timeslot)) {
                    response.sendRedirect(request.getContextPath() + "/jsp/appointmentupdated.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
                }
            } catch (NumberFormatException | NullPointerException e) {
                response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
            }
        }
    }
 
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<TimeSlot> timeSlots = availabilityDAO.getAllTimeSlots(); 
        
        request.setAttribute("timeSlots", timeSlots);

        // Forward the request to the JSP file
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/schedule_dashboard.jsp");
        dispatcher.forward(request, response);
    }
}