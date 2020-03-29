package cz.todo_project.app.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import cz.todo_project.app.enums.PriorityEnum;

public class TaskDTO {
	
	private Long id;
	private String name;
	private LocalDateTime from;
	private LocalDateTime to;
	private LocalDateTime completed;
	private PriorityEnum priority;
	private UserDTO user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	public PriorityEnum getPriority() {
		return priority;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	public String getPlannedTime() {
		long seconds = ChronoUnit.SECONDS.between(from, to);
		long hours = (seconds / (60*60)) % 24;
		long minutes = (seconds / 60) % 60;
		long countedSeconds = seconds % 60;
		return hours + ":" + minutes + ":" + countedSeconds;  
	}
	
	public String getRealTime() {
		if (completed != null) {
		long seconds = ChronoUnit.SECONDS.between(completed, to);
		long hours = (seconds / (60*60)) % 24;
		long minutes = (seconds / 60) % 60;
		long countedSeconds = seconds % 60;
		return hours + ":" + minutes + ":" + countedSeconds;  
		}
		return null;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public LocalDateTime getCompleted() {
		return completed;
	}
	public void setCompleted(LocalDateTime completed) {
		this.completed = completed;
	}
	
	public String getColor() {
		if (PriorityEnum.LOW.equals(priority)) {
			return "low";
		}
		if (PriorityEnum.MEDIUM.equals(priority)) {
			return "medium";
		}
		if (PriorityEnum.HIGH.equals(priority)) {
			return "high";
		}
		if (PriorityEnum.IMMEDIATE.equals(priority)) {
			return "immediate";
		}
		return "";
	}
	@Override
	public int hashCode() {
		return Objects.hash(completed, from, id, name, priority, to, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskDTO other = (TaskDTO) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(from, other.from)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && priority == other.priority
				&& Objects.equals(to, other.to) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", name=" + name + ", from=" + from + ", to=" + to + ", completed=" + completed
				+ ", priority=" + priority + ", user=" + user + "]";
	}
	
	
	
	


	
	
	
	
	

}
