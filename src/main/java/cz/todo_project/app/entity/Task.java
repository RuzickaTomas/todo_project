package cz.todo_project.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cz.todo_project.app.enums.PriorityEnum;


@Entity
@Table(name = "TODO_TASK", schema = "public")
public class Task implements Serializable {

	/**
	 * 
	 */

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "tsk_gen")
	@SequenceGenerator(name="tsk_gen", sequenceName = "todo_task_seq", allocationSize=1)
	@Column
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private LocalDateTime guess;
	
	@Column
	private LocalDateTime real;
	
	@Column 
	private LocalDateTime completed;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private PriorityEnum priority;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id" ,  nullable = true)
	private User user;
	
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
	public LocalDateTime getGuess() {
		return guess;
	}
	public void setGuess(LocalDateTime guess) {
		this.guess = guess;
	}
	public LocalDateTime getReal() {
		return real;
	}
	public void setReal(LocalDateTime real) {
		this.real = real;
	}
	public PriorityEnum getPriority() {
		return priority;
	}
	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCompleted() {
		return completed;
	}
	public void setCompleted(LocalDateTime completed) {
		this.completed = completed;
	}
	@Override
	public int hashCode() {
		return Objects.hash(completed, guess, id, name, priority, real, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(guess, other.guess)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && priority == other.priority
				&& Objects.equals(real, other.real) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", guess=" + guess + ", real=" + real + ", completed=" + completed
				+ ", priority=" + priority + ", user=" + user + "]";
	}
	
	
	
	
	
}
