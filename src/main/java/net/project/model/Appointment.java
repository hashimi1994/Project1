package net.project.model;


import java.time.LocalDateTime;

public class Appointment {

	private int AppointmentID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int professorId;
    private String notes;
    
    
	public Appointment(int AppointmentID, LocalDateTime startTime, LocalDateTime endTime, int professorId,
			String notes) {
		super();
		this.AppointmentID = AppointmentID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.professorId = professorId;
		this.notes = notes;
	}
	public Appointment() {
		
	}
	public int getAppointmentID() {
		return AppointmentID;
	}
	public void setAppointmentID(int id) {
		this.AppointmentID = AppointmentID;
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
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

 	    
}