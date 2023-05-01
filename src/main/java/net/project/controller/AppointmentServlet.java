package net.project.controller;


import java.io.IOException;


import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.project.controller.*;
import net.project.dao.*;
import net.project.model.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/appointmentpage")
public class AppointmentServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private AppointmentDAO appointmentDAO;

	    @Override
	    public void init() throws ServletException {
	        appointmentDAO = new AppointmentDAO();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	       
	        //User sessionUser = (User)session.getAttribute("user");
         //int sessionUserId = sessionUser.getId();
	        
	        if ("schedule".equals(action)) {
	            try {
	            	int id = 0; //Placeholder value that will be asigned by mysql auto increment
	                int timeSlotId = Integer.parseInt(request.getParameter("timeSlotId"));
	                LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
	                LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
	                int professorId = Integer.parseInt(request.getParameter("professorId"));
	                int studentId = Integer.parseInt(request.getParameter("studentId"));
	                String notes = request.getParameter("notes");
	                
	                Appointment appointment = new Appointment(id, startTime, endTime, professorId, studentId, notes);
	                if (appointmentDAO.createAppointment(appointment)) {
	                       
	                    //Update the Timeslot to show it was claimed
	                 TimeSlot timeslot = new TimeSlot(timeSlotId, startTime, endTime, professorId, false);   
	                    
	                 AvailabilityDAO availabilityDAO = new AvailabilityDAO();
	                   
	                 availabilityDAO.claimTimeSlot(timeSlotId);  
	                   
	                 
	                 
	                 response.sendRedirect(request.getContextPath() + "/jsp/appointment_created.jsp");  
	                } else {
	                    response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	                }
	            } catch (NumberFormatException | NullPointerException e) {
	                response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	            }
	    	    } else if ("delete".equals(action)) {
	    	        try {
	    	            int id = Integer.parseInt(request.getParameter("id"));
	    	            Appointment appointment = appointmentDAO.getAppointmentById(id);
	    	            if (appointment != null && appointmentDAO.deleteAppointment(appointment)) {
	    	                response.sendRedirect(request.getContextPath() + "/jsp/appointmentupdated.jsp");
	    	            } else {
	    	                response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	    	            }
	    	        } catch (NumberFormatException | NullPointerException e) {
	    	            response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	    	        }
	    	    
	    	    } else if ("update".equals(action)) {
	    	        try {
	    	            int id = Integer.parseInt(request.getParameter("id"));
	    	            LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
	    	            LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
	    	            int professorId = Integer.parseInt(request.getParameter("professorId"));
	    	            int studentId = Integer.parseInt(request.getParameter("studentId"));
	    	            String notes = request.getParameter("notes");
	    	            String newNotes = request.getParameter("new-notes");
	    	            
	    	            if(newNotes != "") {
	    	            	notes = newNotes;
	    	            }
	    	            
	    	            Appointment appointment = new Appointment(id, startTime, endTime, professorId, studentId ,  notes);
	    	            
	    	            if (appointmentDAO.updateAppointment(appointment, startTime, endTime)) {
	    	                response.sendRedirect(request.getContextPath() + "/jsp/appointmentupdated.jsp");
	    	            } else {
	    	                response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	    	            }
	    	        } catch (NumberFormatException | NullPointerException e) {
	    	            response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
	    	        }
	    	    }
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.getRequestDispatcher("/jsp/appointment_created.jsp").forward(request, response);
	    }
	}