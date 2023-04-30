package net.project.model;
import java.time.LocalDateTime;

public class TimeSlot {
	private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int professorId;
    private boolean claimed;
	public TimeSlot(int id, LocalDateTime startTime, LocalDateTime endTime, int professorId, boolean claimed) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.professorId = professorId;
		this.claimed = claimed;
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
	public boolean isClaimed() {
		return claimed;
	}
	public void setClaimed(boolean claimed) {
		this.claimed = claimed;
	}

   
}


