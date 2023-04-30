<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="net.project.model.TimeSlot"%>
<%@page import="net.project.dao.AvailabilityDAO"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Professor Schedule Dashboard</title>
  <style type="text/css">
		body {
		background-color: black;
		color: white;
		font-family: Arial, sans-serif;
		text-align: center; /* center the page text */
		}
		h1 {
		color: white;
		font-size: 36px; /* increase font size for better visibility */
		margin-top: 50px; /* add margin to separate from the top */
		}
		table {
		margin: auto; /* center the table */
		}
		table, th, td {
		border: 1px solid white; /* update the border color */
		border-collapse: collapse;
		padding: 10px;
		}
		th {
		background-color: #222; /* add background color to table headers */
		color: white;
		}
		td {
		background-color: #444; /* add background color to table cells */
		color: white;
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
                         <input type="hidden" name="id" value="<%= timeSlot.getId() %>">
                         <input type="hidden" name="startTime" value="<%= timeSlot.getStartTime().toString() %>">
                         <input type="hidden" name="endTime" value="<%= timeSlot.getEndTime().toString() %>">
                         <input type="hidden" name="professorId" value="<%= timeSlot.getProfessorId() %>">
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

  <p><a href="professor_dashboard.jsp">Back to Professor Dashboard</a></p>

   <form action="<%= request.getContextPath() %>/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
