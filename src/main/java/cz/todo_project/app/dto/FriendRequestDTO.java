package cz.todo_project.app.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FriendRequestDTO {

	private Long id;
	private Long requestedUserId;
	private Set<UserDTO> users = new HashSet<>();
	private LocalDateTime denied;
	private LocalDateTime accepted;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRequestedUserId() {
		return requestedUserId;
	}
	public void setRequestedUserId(Long requestedUserId) {
		this.requestedUserId = requestedUserId;
	}
	public Set<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	public LocalDateTime getDenied() {
		return denied;
	}
	public void setDenied(LocalDateTime denied) {
		this.denied = denied;
	}
	public LocalDateTime getAccepted() {
		return accepted;
	}
	public void setAccepted(LocalDateTime accepted) {
		this.accepted = accepted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accepted == null) ? 0 : accepted.hashCode());
		result = prime * result + ((denied == null) ? 0 : denied.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((requestedUserId == null) ? 0 : requestedUserId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendRequestDTO other = (FriendRequestDTO) obj;
		if (accepted == null) {
			if (other.accepted != null)
				return false;
		} else if (!accepted.equals(other.accepted))
			return false;
		if (denied == null) {
			if (other.denied != null)
				return false;
		} else if (!denied.equals(other.denied))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requestedUserId == null) {
			if (other.requestedUserId != null)
				return false;
		} else if (!requestedUserId.equals(other.requestedUserId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FriendRequestDTO [id=" + id + ", requestedUserId=" + requestedUserId + ", denied=" + denied
				+ ", accepted=" + accepted + "]";
	}

	
	
	
}
