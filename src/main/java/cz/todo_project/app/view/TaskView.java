package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.dto.UserDTO;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.DefaultMsgService;
import cz.todo_project.app.service.TaskService;
import cz.todo_project.app.service.UserService;

@Component
@ManagedBean
@ViewScoped
public class TaskView implements InitializingBean {
	
	//TODO add validations
	
    @Autowired
    private DefaultMsgService msgService;
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    
    private List<TaskDTO> tasks = new ArrayList<>();
    private TaskDTO newTask;
    
    private List<UserDTO> users = new ArrayList<>();
    
    private boolean toUpdate;
    
    
    public void init() {
    	tasks = taskService.getAll();
    	newTask = new TaskDTO();
    	users = userService.getAll();
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
		taskService.create(newTask);
		} else {	
	        FacesMessage msg = new FacesMessage("Task Edited", newTask.getId() + "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			taskService.update(newTask);
		}
		prepareNewTask();
		refreshList();
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


    //replacement of init @PostConstruct
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
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

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

    
    
}