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
                <td><input type="number" name="professorId" id="professorId" required></td>
            </tr>
            <tr>
                <td><label for="claimed">Claimed:</label></td>
                <td>
                    <input type="checkbox" name="claimed" id="claimed">
                    <label for="claimed">Check if claimed</label>
                </td>
            </tr>
        </table>
        <button type="submit">Submit</button>
    </form>
</body>
</html>