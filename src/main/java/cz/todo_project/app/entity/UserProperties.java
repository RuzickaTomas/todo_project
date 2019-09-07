package cz.todo_project.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cz.todo_project.app.enums.UserRoleEnum;


@Entity
@Table(name = "todo_user_properties", schema = "public")
public class UserProperties implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "usr_prop_gen")
	@SequenceGenerator(name="usr_prop_gen", sequenceName = "todo_user_prop_seq", allocationSize=1)
	@Column
	private Long id;
	
	@Column
	private String password;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
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
		UserProperties other = (UserProperties) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "UserProperties [id=" + id + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
}
