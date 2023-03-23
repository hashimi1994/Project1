

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Student Dashboard</title>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("userFullName") %></h1>
    <h2>Student Dashboard</h2>

    <h3>Actions</h3>
    <ul>
        <li><a href="jsp/view-appointments.jsp">View Appointments</a></li>
        <li><a href="jsp/schedule-appointment.jsp">Schedule Appointment</a></li>
        <li><a href="jsp/update-appointment.jsp">Update Appointment</a></li>
        <li><a href="jsp/cancel-appointment.jsp">Cancel Appointment</a></li>
    </ul>

    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
