<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Availability</title>
</head>
<body>
    <h1>Create Availability</h1>
    <form action="<%= request.getContextPath() %>/professoravailability" method="post">
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