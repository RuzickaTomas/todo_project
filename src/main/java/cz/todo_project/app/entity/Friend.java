package cz.todo_project.app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cz.todo_project.app.enums.FriendStateEnum;

@Entity
@Table(name = "TODO_FRIEND", schema = "public")
public class Friend implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "frd_gen")
	@SequenceGenerator(name="frd_gen", sequenceName = "todo_friend_seq", allocationSize=1)
	@Column
	private Long id;
	
	@Column
	private Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", foreignKey=@ForeignKey(name="todo_friend_fk"))
	private User user;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FriendStateEnum getState() {
		return state;
	}

	public void setState(FriendStateEnum state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, state, user, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		return Objects.equals(id, other.id) && state == other.state && Objects.equals(user, other.user)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", userId=" + userId + ", user=" + user + ", state=" + state + "]";
	}
	
	
	
}
