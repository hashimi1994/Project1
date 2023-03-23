package net.project.dao;
import net.project.model.Appointment;
import net.project.model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

	public List<Appointment> getAllAppointments() {
	    List<Appointment> appointments = new ArrayList<Appointment>();
	    String query = "SELECT * FROM Appointments";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            Appointment appointment = new Appointment();
	            appointment.setId(resultSet.getInt("AppointmentID"));
	            appointment.setStartTime(resultSet.getObject("StartTime", LocalDateTime.class));
	            appointment.setEndTime(resultSet.getObject("EndTime", LocalDateTime.class));
	            appointment.setProfessorId(resultSet.getInt("Professor"));
	            appointment.setStudentId(resultSet.getInt("Student"));
	            appointment.setNotes(resultSet.getString("Notes"));
	            appointments.add(appointment);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error retrieving appointments", e);
	    }
	    return appointments;
	}

	public Appointment getAppointmentById(int id) {
	    String query = "SELECT * FROM Appointments WHERE AppointmentID = ?";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            Appointment appointment = new Appointment();
	            appointment.setId(resultSet.getInt("AppointmentID"));
	            appointment.setStartTime(resultSet.getObject("StartTime", LocalDateTime.class));
	            appointment.setEndTime(resultSet.getObject("EndTime", LocalDateTime.class));
	            appointment.setProfessorId(resultSet.getInt("Professor"));
	            appointment.setStudentId(resultSet.getInt("Student"));
	            appointment.setNotes(resultSet.getString("Notes"));
	            return appointment;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error retrieving appointment by ID", e);
	    }
	    return null;
	}

	public boolean createAppointment(Appointment appointment) {
	    String query = "INSERT INTO Appointments (StartTime, EndTime, Professor, Student, Notes) VALUES (?, ?, ?, ?, ?)";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setObject(1, appointment.getStartTime());
	        preparedStatement.setObject(2, appointment.getEndTime());
	        preparedStatement.setInt(3, appointment.getProfessorId());
	        preparedStatement.setInt(4, appointment.getStudentId());
	        preparedStatement.setString(5, appointment.getNotes());
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        throw new RuntimeException("Error creating appointment", e);
	    }
	}

	public boolean updateAppointment(Appointment appointment) {
	    String query = "UPDATE Appointments SET StartTime = ?, EndTime = ?, Professor = ?, Student = ?, Notes = ? WHERE AppointmentID = ?";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setObject(1, appointment.getStartTime());
	        preparedStatement.setObject(2, appointment.getEndTime());
	        preparedStatement.setInt(3, appointment.getProfessorId());
	        preparedStatement.setInt(4, appointment.getStudentId());
	        preparedStatement.setString(5, appointment.getNotes());
	        preparedStatement.setInt(6, appointment.getId());
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        throw new RuntimeException("Error updating appointment", e);
	    }
	}
    public boolean deleteAppointment(int id) {
        String query = "DELETE FROM Appointments WHERE AppointmentID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting appointment", e);
        }
    }
}