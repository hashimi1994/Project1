<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="net.project.model.Appointment"%>
<%@page import="net.project.dao.AppointmentDAO"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="net.project.model.User"%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Scheduled Appointments</title>
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
    <h2>Scheduled Appointments</h2>

    <h3>Your List of Appointments</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>            
                <th>Start Time</th>
                <th>End Time</th>
                <th>Student ID</th>
                <th>Notes</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Appointment> appointmentList = null;
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            try {
                appointmentList = appointmentDAO.getAllAppointments();
            } catch (Exception e) {
                out.println("Error retrieving appointments: " + e.getMessage());
            }
            if (appointmentList == null || appointmentList.isEmpty()) {
            %>
                <tr>
                    <td colspan="6">No appointments available.</td>
                </tr>
            <% 
            } else {
            	User sessionUser = (User)session.getAttribute("user");
            	int sessionUserId = sessionUser.getId();
                for (Appointment appointment : appointmentList) {
                	if(appointment.getProfessorId() == sessionUserId){
            %>
                <tr>
                    <td><%= appointment.getid() %></td>
                    <td><%= appointment.getStartTime() %></td>
                    <td><%= appointment.getEndTime() %></td>
                    <td><%= appointment.getStudentId() %></td>
                    <td><%= appointment.getNotes() %></td>
                    <td>
                        <form action="<%= request.getContextPath() %>/appointmentpage" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="<%= appointment.getid() %>">
                            <input type="hidden" name="professorId" value="<%= sessionUserId %>">
                            <input type="hidden" name="studentId" value="<%= appointment.getProfessorId() %>" >
                            <input type="hidden" name="notes" value="<%= appointment.getNotes() %>">
                            Start Time: <input type="datetime-local" name="startTime" value="<%= appointment.getStartTime().toString().replace("T", " ") %>">
                            End Time: <input type="datetime-local" name="endTime" value="<%= appointment.getEndTime().toString().replace("T", " ") %>">
                            <input type="text" name="new-notes" placeholder="NEW NOTES">
                            <input type="submit" value="Update Appointment">
                        </form>
                    </td>
                </tr>
            <% 
                	}
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