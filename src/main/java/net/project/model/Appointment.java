package net.project.model;


import java.time.LocalDateTime;

public class Appointment {

	private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int professorId;
    private int studentId;
    private String notes;
    private int appointmentID;
    
	public Appointment(int id, LocalDateTime startTime, LocalDateTime endTime, int professorId, int studentId,
			String notes) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.professorId = professorId;
		this.studentId = studentId;
		this.notes = notes;
	}
	public Appointment() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public void setAppointmentID(int int1) {
		
		
	}

 	    
}