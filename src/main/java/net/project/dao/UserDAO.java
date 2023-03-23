package net.project.dao;
import net.project.model.User;
import net.project.model.User;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.project.model.DBConnection;

public class UserDAO {
	public User login(String username, String password) {
        String query = "SELECT * FROM Students WHERE Username = ? AND Pass = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("StudentID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Pass"));
                user.setEmail(resultSet.getString("Email"));
                user.setFirstName(resultSet.getString("StudentFirstName"));
                user.setLastName(resultSet.getString("StudentLastName"));
                user.setUserType("student");
                return user;
            } else {
                query = "SELECT * FROM Professors WHERE Username = ? AND Pass = ?";
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(query)) {
                    preparedStatement2.setString(1, username);
                    preparedStatement2.setString(2, password);
                    resultSet = preparedStatement2.executeQuery();

                    if (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt("ProfessorCode"));
                        user.setUsername(resultSet.getString("Username"));
                        user.setPassword(resultSet.getString("Pass"));
                        user.setEmail(resultSet.getString("Email"));
                        user.setFirstName(resultSet.getString("ProfessorFirstName"));
                        user.setLastName(resultSet.getString("ProfessorLastName"));
                        user.setUserType("professor");
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking login", e);
        }
        return null;
    }

    public List<User> getAllStudents() {
       List<User> students = new ArrayList<User>();
        String query = "SELECT * FROM Students";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User student = new User();
                student.setId(resultSet.getInt("StudentID"));
                student.setUsername(resultSet.getString("Username"));
                student.setPassword(resultSet.getString("Pass"));
                student.setEmail(resultSet.getString("Email"));
                student.setFirstName(resultSet.getString("StudentFirstName"));
                student.setLastName(resultSet.getString("StudentLastName"));
                student.setUserType("student");
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving students", e);
        }
        return students;
    }

    public List<User> getAllProfessors() {
        List<User> professors = new ArrayList<User>();
        String query = "SELECT * FROM Professors";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User professor = new User();
                professor.setId(resultSet.getInt("ProfessorCode"));
                professor.setUsername(resultSet.getString("Username"));
                professor.setPassword(resultSet.getString("Pass"));
                professor.setEmail(resultSet.getString("Email"));
                professor.setFirstName(resultSet.getString("ProfessorFirstName"));
                professor.setLastName(resultSet.getString("ProfessorLastName"));
                professor.setUserType("professor");
                professors.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving professors", e);
        }
        return professors;
    }
    public User getStudentById(int id) {
        String query = "SELECT * FROM Students WHERE StudentID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User student = new User();
                student.setId(resultSet.getInt("StudentID"));
                student.setUsername(resultSet.getString("Username"));
                student.setPassword(resultSet.getString("Pass"));
                student.setEmail(resultSet.getString("Email"));
                student.setFirstName(resultSet.getString("StudentFirstName"));
                student.setLastName(resultSet.getString("StudentLastName"));
                student.setUserType("student");
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving student by ID", e);
        }
        return null;
    }

    public User getProfessorById(int id) {
        String query = "SELECT * FROM Professors WHERE ProfessorCode = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User professor = new User();
                professor.setId(resultSet.getInt("ProfessorCode"));
                professor.setUsername(resultSet.getString("Username"));
                professor.setPassword(resultSet.getString("Pass"));
                professor.setEmail(resultSet.getString("Email"));
                professor.setFirstName(resultSet.getString("ProfessorFirstName"));
                professor.setLastName(resultSet.getString("ProfessorLastName"));
                professor.setUserType("professor");
                return professor;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving professor by ID", e);
        }
        return null;
    }

    public boolean createStudent(User student) {
        String query = "INSERT INTO Students (Username, Pass, Email, StudentFirstName, StudentLastName) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getFirstName());
            preparedStatement.setString(5, student.getLastName());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating student", e);
        }
    }

    public boolean createProfessor(User professor) {
        String query = "INSERT INTO Professors (Username, Pass, Email, ProfessorFirstName, ProfessorLastName) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, professor.getUsername());
            preparedStatement.setString(2, professor.getPassword());
            preparedStatement.setString(3, professor.getEmail());
            preparedStatement.setString(4, professor.getFirstName());
            preparedStatement.setString(5, professor.getLastName());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating professor", e);
        }
    }
    public boolean updateStudent(User student) {
        String query = "UPDATE Students SET Username = ?, Pass = ?, Email = ?, StudentFirstName = ?, StudentLastName = ? WHERE StudentID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getFirstName());
            preparedStatement.setString(5, student.getLastName());
            preparedStatement.setInt(6, student.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student", e);
        }
}
    public boolean updateProfessor(User professor) {
        String query = "UPDATE Professors SET Username = ?, Pass = ?, Email = ?, ProfessorFirstName = ?, ProfessorLastName = ? WHERE ProfessorCode = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, professor.getUsername());
            preparedStatement.setString(2, professor.getPassword());
            preparedStatement.setString(3, professor.getEmail());
            preparedStatement.setString(4, professor.getFirstName());
            preparedStatement.setString(5, professor.getLastName());
            preparedStatement.setInt(6, professor.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating professor", e);
        }
    }
}