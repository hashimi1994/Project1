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
        if ("schedule".equals(action)) {
            try {
                int AppointmentID = Integer.parseInt(request.getParameter("AppointmentID"));
                LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
                LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
                int professorId = Integer.parseInt(request.getParameter("professorId"));
                String notes = request.getParameter("notes");               
                Appointment appointment = new Appointment(AppointmentID, startTime, endTime, professorId, notes);
                if (appointmentDAO.createAppointment(appointment)) {
                    response.sendRedirect(request.getContextPath() + "/jsp/appointment_created.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
                }
            } catch (NumberFormatException | NullPointerException e) {
                response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
        }
    }
                
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            // Display all appointments
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("jsp/student_dashboard.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            // Display the form to edit an appointment
            int id = Integer.parseInt(request.getParameter("id"));
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("jsp/student_dashboard.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            // Delete the appointment and redirect to the appointments page
            int id = Integer.parseInt(request.getParameter("id"));
            appointmentDAO.deleteAppointment(id);
            response.sendRedirect(request.getContextPath() + "/appointmenpage");
        }
    }
}