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
                appointment.setid(resultSet.getInt("id"));
                appointment.setStartTime(resultSet.getObject("StartTime", LocalDateTime.class));
                appointment.setEndTime(resultSet.getObject("EndTime", LocalDateTime.class));
                appointment.setProfessorId(resultSet.getInt("ProfessorId"));
                appointment.setStudentId(resultSet.getInt("StudentId"));
                appointment.setNotes(resultSet.getString("Notes"));
                appointments.add(appointment);
            }
        }catch (SQLException e) {
            e.printStackTrace(); // Add this line to print the stack trace in the logs
            throw new RuntimeException("Error retrieving appointments: " + e.getMessage(), e);
        }
        return appointments;
    }

    public Appointment getAppointmentById(int id) {
        String query = "SELECT * FROM Appointments WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setid(resultSet.getInt("id"));
                appointment.setStartTime(resultSet.getObject("startTime", LocalDateTime.class));
                appointment.setEndTime(resultSet.getObject("endTime", LocalDateTime.class));
                appointment.setProfessorId(resultSet.getInt("professorId"));
                appointment.setNotes(resultSet.getString("Notes"));
                return appointment;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving appointment by ID", e);
        }
        return null;
    }

    public boolean createAppointment(Appointment appointment) {
    	
        String query = "INSERT INTO Appointments (id, startTime, endTime, professorId, studentId, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, appointment.getid());
            preparedStatement.setObject(2, appointment.getStartTime());
            preparedStatement.setObject(3, appointment.getEndTime());
            preparedStatement.setInt(4, appointment.getProfessorId());
            preparedStatement.setInt(5, appointment.getStudentId());
            preparedStatement.setString(6, appointment.getNotes());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating appointment", e);
        }
    }
    public boolean updateAppointment(Appointment appointment, LocalDateTime startTime, LocalDateTime endTime) {
        String query = "UPDATE Appointments SET startTime = ?, endTime = ?, professorId = ?, notes = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, startTime);
            preparedStatement.setObject(2, endTime);
            preparedStatement.setInt(3, appointment.getProfessorId());
            preparedStatement.setString(4, appointment.getNotes());
            preparedStatement.setObject(5, appointment.getid());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating appointment", e);
        }
    }
    

    public boolean deleteAppointment(Appointment appointment) {
        String query = "DELETE FROM Appointments WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, appointment.getid());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting appointment", e);
        }
        
    }
}