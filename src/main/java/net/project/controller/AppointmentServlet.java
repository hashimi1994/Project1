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

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/AppointmentServlet"})
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        switch (action) {
            case "create":
                createAppointment(request, response);
                break;
            case "update":
                updateAppointment(request, response);
                break;
            case "delete":
                deleteAppointment(request, response);
                break;
            default:
                response.sendRedirect("jsp/login.jsp");
                break;
        }
    }

    private void createAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        if (startTimeStr == null || startTimeStr.isEmpty() || endTimeStr == null || endTimeStr.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid start or end time. Please try again.");
            request.getRequestDispatcher("jsp/schedule_dashboard.jsp").forward(request, response);
            return;
        }

        LocalDateTime startTime = Timestamp.valueOf(startTimeStr).toLocalDateTime();
        LocalDateTime endTime = Timestamp.valueOf(endTimeStr).toLocalDateTime();
        // rest of the method
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        if (startTimeStr == null || startTimeStr.isEmpty() || endTimeStr == null || endTimeStr.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid start or end time. Please try again.");
            request.getRequestDispatcher("jsp/schedule_dashboard.jsp").forward(request, response);
            return;
        }

        LocalDateTime startTime = Timestamp.valueOf(startTimeStr).toLocalDateTime();
        LocalDateTime endTime = Timestamp.valueOf(endTimeStr).toLocalDateTime();
        // rest of the method
    }

    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int appointmentID = Integer.parseInt(request.getParameter("appointmentID"));

        if (appointmentDAO.deleteAppointment(appointmentID)) {
            response.sendRedirect("jsp/student_dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Appointment deletion failed. Please try again.");
            request.getRequestDispatcher("jsp/student_dashboard.jsp").forward(request, response);
        }
    }
}
