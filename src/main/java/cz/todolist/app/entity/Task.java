package cz.todolist.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
	private Long guess;
	
	@Column
	private Long real;
	
	@Column
	private Long priority;
	
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
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
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
		Task other = (Task) obj;
		return Objects.equals(guess, other.guess) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(priority, other.priority) && Objects.equals(real, other.real);
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", guess=" + guess + ", real=" + real + ", priority=" + priority
				+ "]";
	}

	
	
	
}
