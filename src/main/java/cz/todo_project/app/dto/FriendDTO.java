package cz.todo_project.app.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import cz.todo_project.app.enums.FriendStateEnum;

public class FriendDTO {

	private Long id;
	private Long userId;
	private Set<UserDTO> users = new HashSet<UserDTO>();
	private FriendStateEnum state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Set<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	public FriendStateEnum getState() {
		return state;
	}
	public void setState(FriendStateEnum state) {
		this.state = state;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, state, users, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendDTO other = (FriendDTO) obj;
		return Objects.equals(id, other.id) && state == other.state && Objects.equals(users, other.users)
				&& Objects.equals(userId, other.userId);
	}
	
	@Override
	public String toString() {
		return "FriendDTO [id=" + id + ", userId=" + userId + ", user=" + users + ", state=" + state + "]";
	}
	
	
	
}
