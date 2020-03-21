package cz.todo_project.app.dto;

import java.util.Objects;

import cz.todo_project.app.enums.PriorityEnum;

public class TaskDTO {
	
	private Long id;
	private String name;
	private Long guess;
	private Long real;
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
	public Long getGuess() {
		return guess;
	}
	public void setGuess(Long guess) {
		this.guess = guess;
	}
	public Long getReal() {
		return real;
	}
	public void setReal(Long real) {
		this.real = real;
	}
	public PriorityEnum getPriority() {
		return priority;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public String getColor() {
		if (PriorityEnum.LOW.name().equals(priority)) {
			return "low";
		}
		if (PriorityEnum.MEDIUM.name().equals(priority)) {
			return "medium";
		}
		if (PriorityEnum.HIGH.name().equals(priority)) {
			return "high";
		}
		if (PriorityEnum.IMMEDIATE.name().equals(priority)) {
			return "immediate";
		}
		return "";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(guess, id, name, priority, real);
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
		return Objects.equals(guess, other.guess) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(priority, other.priority) && Objects.equals(real, other.real);
	}
	
	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", name=" + name + ", guess=" + guess + ", real=" + real + ", priority=" + priority
				+ "]";
	}
	
	
	
	
	

}
