package net.project.dao;
import net.project.model.DBConnection;
import net.project.model.TimeSlot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityDAO {
	public List<TimeSlot> getAllTimeSlots() {
	    List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	    String query = "SELECT * FROM ProfTimeSlots";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int timeSlotID = resultSet.getInt("TimeSlotID");
	            LocalDateTime startTime = resultSet.getTimestamp("StartTime").toLocalDateTime();
	            LocalDateTime endTime = resultSet.getTimestamp("EndTime").toLocalDateTime();
	            int professor = resultSet.getInt("Professor");
	            boolean claimed = resultSet.getBoolean("Claimed");
	            TimeSlot timeSlot = new TimeSlot(timeSlotID, startTime, endTime, professor, claimed);
	            timeSlots.add(timeSlot);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error retrieving time slots", e);
	    }
	    return timeSlots;
	}

	public TimeSlot getTimeSlotById(int id) {
	    String query = "SELECT * FROM ProfTimeSlots WHERE TimeSlotID = ?";
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int timeSlotID = resultSet.getInt("TimeSlotID");
	            LocalDateTime startTime = resultSet.getTimestamp("StartTime").toLocalDateTime();
	            LocalDateTime endTime = resultSet.getTimestamp("EndTime").toLocalDateTime();
	            int professor = resultSet.getInt("Professor");
	            boolean claimed = resultSet.getBoolean("Claimed");
	            TimeSlot timeSlot = new TimeSlot(timeSlotID, startTime, endTime, professor, claimed);
	            return timeSlot;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error retrieving time slot by ID", e);
	    }
	    return null;
	}

    public boolean createTimeSlot(TimeSlot timeSlot) {
        String query = "INSERT INTO ProfTimeSlots (StartTime, EndTime, Professor, Claimed) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(timeSlot.getStartTime()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(timeSlot.getEndTime()));
            preparedStatement.setInt(3, timeSlot.getProfessorId());
            preparedStatement.setBoolean(4, timeSlot.isClaimed());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating time slot", e);
        }
    }

    public boolean updateTimeSlot(TimeSlot timeSlot) {
        String query = "UPDATE ProfTimeSlots SET StartTime = ?, EndTime = ?, Professor = ?, Claimed = ? WHERE TimeSlotID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(timeSlot.getStartTime()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(timeSlot.getEndTime()));
            preparedStatement.setInt(3, timeSlot.getProfessorId());
            preparedStatement.setBoolean(4, timeSlot.isClaimed());
            preparedStatement.setInt(5, timeSlot.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating time slot", e);
        }
    
    }
    public boolean deleteTimeSlot(int id) {
        String query = "DELETE FROM ProfTimeSlots WHERE TimeSlotID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting time slot", e);
        }
    }
}
    