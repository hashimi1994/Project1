<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Professor Dashboard</title>
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
    <h1>Welcome </h1>
    <h2>Professor Dashboard</h2>

    <h3>Actions</h3>
    <ul>
        <li><a href="schedule_dashboard.jsp">View Appointments</a></li>
        <li><a href="create_availability.jsp">Create Availability</a></li>
        <li><a href="delete_availability.jsp">Delete Availability</a></li>
    </ul>

    <form action="<%= request.getContextPath() %>/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>