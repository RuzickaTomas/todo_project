package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.dto.UserDetailsImpl;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.DefaultMsgService;
import cz.todo_project.app.service.TaskService;
import cz.todo_project.app.service.UserService;

@Component
@ManagedBean
@ViewScoped
public class TaskView {
	
	//TODO add validations
	
    @Autowired
    private DefaultMsgService msgService;
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    
    private List<TaskDTO> tasks = new ArrayList<>();
    private TaskDTO newTask;
    
    private String filteredUser;
    private Set<String> filteredUsers;
    
    private String defaultUser = Long.valueOf(0L).toString();
    
    private List<UserDTO> users = new ArrayList<>();
    private UserDTO currentUser = null;
    
    
    private boolean toUpdate;
    
    @PostConstruct
    public void init() {
    	tasks = taskService.getAll();
    	newTask = new TaskDTO();
    	users = userService.getAll();
    	currentUser = userService.getByUsername(getAuthUsername());
    	refreshUser();
    }
    
    
    public List<TaskDTO> getTasks() {
		return tasks;
	}


	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}
	
	
	 public TaskDTO getNewTask() {
		return newTask;
	}

	public void setNewTask(TaskDTO newTask) {
		this.newTask = newTask;
	}
	
	
	public void prepareNewTask() {
		newTask = new TaskDTO();
		toUpdate = false;
	}
	
	
	public boolean isToUpdate() {
		return toUpdate;
	}

	public void setToUpdate(boolean toUpdate) {
		this.toUpdate = toUpdate;
	}

	public void saveTask() {
		if (!toUpdate && newTask.getId() == null) {
		Long guess = newTask.getGuess() != null ? newTask.getGuess() : 0; 
		Double temp = guess.doubleValue() * 1.2;
		newTask.setReal(temp.longValue());
        FacesMessage msg = new FacesMessage("Task Created", newTask.getId() + "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		if (newTask.getUser() == null) { 
			newTask.setUser(getCurrentUser());
		}  
		taskService.create(newTask);
		} else {	
	        FacesMessage msg = new FacesMessage("Task Edited", newTask.getId() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			taskService.update(newTask);
		}
		prepareNewTask();
		refreshList();
		refreshUser();
	}
	
	
	
	public void updateTask(TaskDTO task) {
		toUpdate = task.getId() != null;
		setNewTask(task);
	}
	
	
	public void deleteTask(Long id) {
		taskService.delete(id);
		refreshList();
	}

	 
	 public PriorityEnum[] getPriorityEnums() {
		 return PriorityEnum.values(); 
	 }


	public String getMsg() {
        return msgService.getMsg();
    }

    
	
	public void onLoad(AjaxBehaviorEvent event) {
		init();
	}

	public void refresh(AjaxBehaviorEvent event) {
		refreshList();
	}

	private void refreshList() {
    	tasks = taskService.getAll();
	}
	
	private void refreshUser() {
		filteredUsers = tasks.stream().filter(p -> p.getUser() !=  null).map(TaskDTO::getUser).map(m -> m.getId().toString()).collect(Collectors.toSet());
	}
	

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public String getFilteredUser() {
		return filteredUser;
	}

	public void setFilteredUser(String filteredUser) {
		this.filteredUser = filteredUser;
	}

	public Set<String> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(Set<String> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}
	
	public void onUserChange() {
		refreshList();
		filterUser(null);
	}
	
	public void filterUser(UserDTO user) {
		if (user == null) {
		 if (!Long.valueOf(0L).toString().equals(filteredUser)) {
		 	tasks =	tasks.stream().filter(filterUserPredicate(filteredUser)).collect(Collectors.toList());
		 	return;
		 } 
		} else {
		 	tasks =	tasks.stream().filter(filterUserPredicate(user)).collect(Collectors.toList());	
		}
	}
	
	private Predicate<TaskDTO> filterUserPredicate(Object o) {
		if (o instanceof UserDTO) {
			return t -> t.getUser() != null && t.getUser().equals(o);
		} 
		if (o instanceof String) {
			return t -> t.getUser() != null && t.getUser().getId().toString().equals(o);
		}
		return null;		
	}

	public String getDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
	
	private UserDTO getCurrentUser() {
	 	return userService.getByUsername(getAuthUsername());
	}
	
	
	private String getAuthUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() ? ((UserDetailsImpl) auth.getPrincipal()).getUsername() : null;
	}
	
	
	

    
    
}