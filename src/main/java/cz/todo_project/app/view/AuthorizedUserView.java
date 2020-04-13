package cz.todo_project.app.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cz.todo_project.app.dto.FriendDTO;
import cz.todo_project.app.dto.FriendRequestDTO;
import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.entity.Friend;
import cz.todo_project.app.entity.FriendRequest;
import cz.todo_project.app.enums.FriendStateEnum;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.FriendRequestService;
import cz.todo_project.app.service.FriendService;
import cz.todo_project.app.service.UserService;
import cz.todo_project.app.view.converter.CollectionsTransformUtil;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthorizedUserView {
	
	@Autowired
	private TaskView taskView;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private FriendRequestService friendRequestService;
	
	private UserDTO currentUser;
	
	private UserDTO requestFriend;
	
	private final List<TaskDTO> tasks = new ArrayList<>();
	
	private List<FriendRequestDTO> requests = new ArrayList<FriendRequestDTO>();
	
	
	@PostConstruct
	public void init() {     	
		if (!getAuthUsername().isBlank()) {
			currentUser = userService.getByUsername(getAuthUsername());
			requests = friendRequestService.getRequestsById(currentUser.getId());
		}
	}
	
	public List<UserDTO> requestedUsers(FriendRequestDTO req) {
		List<UserDTO> result = new ArrayList<>(req.getUsers());
		return result;
	}
	

	public UserDTO getRequestFriend() {
		return requestFriend;
	}

	public void setRequestFriend(UserDTO requestFriend) {
		this.requestFriend = requestFriend;
	}

	public UserDTO getCurrentUser() {
		return currentUser;
	}
	
	public List<TaskDTO> getTasks() {
		tasks.clear();
		if (getAuthUsername() != null) {
			 taskView.setFilteredUser(currentUser.getEmail());
			 taskView.filterUser(currentUser);
			 tasks.addAll(taskView.getTasks());
		}
		return tasks;
	}
	
	public List<UserDTO> getUsers() {
		List<UserDTO> friends = new ArrayList<UserDTO>();
		if (getAuthUsername() != null) {		
			currentUser = userService.getByUsername(getAuthUsername());
			friends.add(currentUser);
		}
		return friends;
	}
	
	public String sortByPriority(PriorityEnum prio) {
		return prio.name();
	}
	
	public List<UserDTO> findUsers(String query) {
		 List<UserDTO> allUsers = userService.getAll();
		 return allUsers.parallelStream().filter(user -> user.getName().contains(query) || user.getSurname().contains(query) || user.getEmail().contains(query)).collect(Collectors.toList());	 
	}
	
	public void acceptRequest(FriendRequestDTO friendRequest) {
		 friendRequest.setAccepted(LocalDateTime.now());
		 friendRequestService.update(friendRequest);
		 FriendDTO friend = new FriendDTO();
		 friend.setUserId(friendRequest.getRequestedUserId());
		 friend.setState(FriendStateEnum.ACCEPTED);
		 currentUser.addFriend(friend);
		 friendService.update(friend);
	}
	
	public void denyRequest(FriendRequestDTO friendRequest) {
		 friendRequest.setDenied(LocalDateTime.now());
		 friendRequestService.update(friendRequest);
	}

	public List<FriendRequestDTO> getRequests() {
		return requests;
	}


	public void setRequests(List<FriendRequestDTO> requests) {
		this.requests = requests;
	}


	public void sendInvitation() {
		if (requestFriend == null) {
			return;
		}
		System.out.println( "Invitation was sent" + requestFriend.getId());
		FriendRequestDTO request = new FriendRequestDTO();
		request.setRequestedUserId(requestFriend.getId());
		currentUser.addFriendRequest(request);
		userService.update(currentUser);
	}
	
	
	
	private String getAuthUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() ? ((UserDetailsImpl) auth.getPrincipal()).getUsername() : null;
	}
	
}
