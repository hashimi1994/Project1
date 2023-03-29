<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor Dashboard</title>
</head>
<body>
    <h1>Welcome </h1>
    <h2>Professor Dashboard</h2>

    <h3>Actions</h3>
    <ul>
        <li><a href="schedule_dashboard.jsp">View Appointments</a></li>
        <li><a href="create_availability.jsp">Create Availability</a></li>
        <li><a href="update-availability.jsp">Update Availability</a></li>
        <li><a href="delete-availability.jsp">Delete Availability</a></li>
    </ul>

    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>