package cz.todo_project.app.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UserDTO {

	private Long id;
	
	private String name;
	
	private String surname;
	
	private LocalDate valid_to;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getValid_to() {
		return valid_to;
	}
	public void setValid_to(LocalDate valid_to) {
		this.valid_to = valid_to;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, valid_to);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname) && Objects.equals(valid_to, other.valid_to);
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", valid_to=" + valid_to + "]";
	}
	
	
	
	
	
	
	
}
