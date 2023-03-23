

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <h1>Welcome, <%= session.getAttribute("userFullName") %></h1>
    <h2>Schedule Dashboard</h2>

    <h3>Available Time Slots</h3>
    <table>
        <thead>
            <tr>
                <th>Professor</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%-- Example of a time slot (Replace this with dynamic data from the database) --%>
            <tr>
                <td>Professor Name</td>
                <td>2023-03-18 10:00:00</td>
                <td>2023-03-18 11:00:00</td>
                <td>
                    <form action="AppointmentServlet" method="post">
                        <input type="hidden" name="action" value="schedule">
                        <input type="hidden" name="timeslotId" value="1">
                        <input type="submit" value="Schedule Appointment">
                    </form>
                </td>
            </tr>
        </tbody>
    </table>

    <p><a href="student_dashboard.jsp">Back to Student Dashboard</a></p>

    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
