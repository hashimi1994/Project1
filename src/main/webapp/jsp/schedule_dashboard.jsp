<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="net.project.model.TimeSlot"%>
<%@page import="net.project.dao.AvailabilityDAO"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Schedule Dashboard</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }
    </style>
</head>
<body>
    <h1>Welcome</h1>
    <h2>Schedule Dashboard</h2>

    <h3>Available Time Slots</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>            
                <th>Start Time</th>
                <th>End Time</th>
                <th>Professor ID</th>
                <th>Claimed</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<TimeSlot> timeSlots = null;
            AvailabilityDAO availabilityDAO = new AvailabilityDAO();
            try {
                 timeSlots = availabilityDAO.getAllTimeSlots();
              
                request.setAttribute("timeSlots", timeSlots);
            } catch (Exception e) {
                out.println("Error retrieving time slots: " + e.getMessage());
            }
            if (timeSlots == null || timeSlots.isEmpty()) {
                // Handle the case when the timeSlots list is empty
            %>
            <tr>
                <td colspan="6">No time slots available.</td>
            </tr>
            <% 
            } else {
                // Loop through the timeSlots list and display each time slot in a row of the table
                for (TimeSlot timeSlot : timeSlots) {
            %>
            <tr>
                <td><%= timeSlot.getId() %></td>
                <td><%= timeSlot.getStartTime() %></td>
                <td><%= timeSlot.getEndTime() %></td>
                <td><%= timeSlot.getProfessorId() %></td>
                <td><%= timeSlot.isClaimed() %></td>
                <td>
                    <form action="<%= request.getContextPath() %>/appointmentpage" method="post">
                         <input type="hidden" name="action" value="schedule">
                         <input type="hidden" name="AppointmentID" value="<%= timeSlot.getId() %>">
                         <input type="hidden" name="startTime" value="<%= timeSlot.getStartTime().toString() %>">
                         <input type="hidden" name="endTime" value="<%= timeSlot.getEndTime().toString() %>">
                         <input type="hidden" name="professorId" value="<%= timeSlot.getProfessorId() %>">
                         <input type="hidden" name="claimed" value="<%= timeSlot.isClaimed() %>">
                         <input type="submit" value="Schedule Appointment">
                    </form>
                </td>
            </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>

    <p><a href="jsp/student_dashboard.jsp">Back to Student Dashboard</a></p>

    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
