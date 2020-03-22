package cz.todo_project.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import cz.todo_project.app.enums.UserRoleEnum;

public class UserDTO {

	private Long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private LocalDate valid_to;
	
	private Set<FriendDTO> friends = new HashSet<>();
	
	private Set<FriendRequestDTO> friendRequests = new HashSet<>();;
 	
	private UserPropertiesDTO properties;
		
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
	
	public UserPropertiesDTO getProperties() {
		return properties;
	}
	public void setProperties(UserPropertiesDTO properties) {
		this.properties = properties;
	}
	
	public void addFriendRequest(FriendRequestDTO request) {
		friendRequests.add(request);
		request.getUsers().add(this);
	}
	
	public void removeFriendRequest(FriendRequestDTO request) {
		friendRequests.remove(request);
		request.getUsers().remove(this);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<FriendDTO> getFriends() {
		return friends;
	}
	public void setFriends(Set<FriendDTO> friends) {
		this.friends = friends;
	}
	
	public Set<FriendRequestDTO> getFriendRequests() {
		return friendRequests;
	}
	public void setFriendRequests(Set<FriendRequestDTO> friendRequests) {
		this.friendRequests = friendRequests;
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
