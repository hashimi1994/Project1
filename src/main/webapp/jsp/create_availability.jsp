<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="net.project.model.User"%>
<%@page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Availability</title>
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

<%
User sessionUser = (User)session.getAttribute("user");
int sessionUserId = sessionUser.getId();
%>



<body>
    <h1>Create Availability</h1>
    <form action="<%= request.getContextPath() %>/professoravailability" method="post">
        <!-- Add this hidden input field for the action -->
        <input type="hidden" name="action" value="submit">

        <table>
            <tr>
                <td><label for="startTime">Start Time:</label></td>
                <td><input type="datetime-local" name="startTime" id="startTime" required></td>
            </tr>
            <tr>
                <td><label for="endTime">End Time:</label></td>
                <td><input type="datetime-local" name="endTime" id="endTime" required></td>
            </tr>
            <tr>
                <td><label for="professorId">Professor ID:</label></td>
                <td><%= sessionUserId %><input type="hidden" name="professorId" id="professorId" value="<%= sessionUserId %>"></td>
            </tr>
        </table>
        <button type="submit">Submit</button>
    </form>
    <p><a href="professor_dashboard.jsp">Back to Professor Dashboard</a></p>
    <form action="<%= request.getContextPath() %>/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>