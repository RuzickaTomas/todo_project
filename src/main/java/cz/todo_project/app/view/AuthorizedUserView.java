package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.TaskService;
import cz.todo_project.app.service.UserService;


@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthorizedUserView {

	@Autowired
	UserView userView;
	
	@Autowired
	private TaskView taskView;
	
	@Autowired
	private UserService userService;
	
	private UserDTO currentUser;
	
	private final List<TaskDTO> tasks = new ArrayList<>();
	
	
	@PostConstruct
	public void init() {     	
		if (!getAuthUsername().isBlank()) {
			currentUser = userService.getByUsername(getAuthUsername());
		}

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
		if (getAuthUsername() != null) {
			userView.getUsers().clear();
			currentUser = userService.getByUsername(getAuthUsername());
			userView.getUsers().add(currentUser);
		}
		return userView.getUsers();
	}
	
	public String sortByPriority(PriorityEnum prio) {
		return prio.name();
	}

	
	private String getAuthUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() ? ((UserDetailsImpl) auth.getPrincipal()).getUsername() : null;
	}
	
}
