package cz.todo_project.app.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "todo_user", schema = "public")
public class User implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "usr_gen")
	@SequenceGenerator(name="usr_gen", sequenceName = "todo_user_seq", allocationSize=1)
	@Column
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
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
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname) && Objects.equals(valid_to, other.valid_to);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", valid_to=" + valid_to + "]";
	}
	
	
	
	
	

}
