package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.service.TaskService;
import cz.todo_project.app.service.UserService;


@Component
@ManagedBean
@ViewScoped
public class AuthorizedUserView {

	@Autowired
	UserView userView;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	private UserDTO currentUser;
	
	private List<TaskDTO> tasks = new ArrayList<>();
	
	@PostConstruct
	public void init() {     	
	}
	
	public UserDTO getCurrentUser() {
		return currentUser;
	}
	
	public List<TaskDTO> getTasks() {
		if (getAuthUsername() != null) {
			tasks.clear();
			tasks.addAll(taskService.getByUsername(getAuthUsername()));
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
	
	public void clearUsername() {
		currentUser = null;
		PrimeFaces.current().executeScript("this.action=#{request.contextPath.concat('/logout')}");
	}
	
	private String getAuthUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() ? ((UserDetailsImpl) auth.getPrincipal()).getUsername() : null;
	}
	
}
