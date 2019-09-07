package cz.todo_project.app.dto;

import java.util.Objects;

import cz.todo_project.app.enums.UserRoleEnum;

public class UserPropertiesDTO {

	private Long id;
	private String password;
	private UserRoleEnum role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRoleEnum getRole() {
		return role;
	}
	public void setRole(UserRoleEnum role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, password, role);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPropertiesDTO other = (UserPropertiesDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password) && role == other.role;
	}
	
	@Override
	public String toString() {
		return "UserPropertiesDTO [id=" + id + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
}
