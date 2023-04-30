<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <h1>Office Hours Calendar Login</h1>
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
    <form action="<%= request.getContextPath() %>/loginuser" method="post">
        <table>
            <tr>
                <td><label for="username">Username:</label></td>
                <td><input type="text" name="username" id="username" required></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" name="password" id="password" required></td>
            </tr>
            <tr>
                <td><label for="userType">User Type:</label></td>
                <td>
                    <select name="userType" id="userType" required>
                        <option value="">Select User Type</option>
                        <option value="student">Student</option>
                        <option value="professor">Professor</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
    <p>Don't have an account? <a href="jsp/register.jsp">Register here</a>.</p>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    </body>
</html>