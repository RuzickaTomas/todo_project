package cz.todo_project.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.enums.PriorityEnum;
import cz.todo_project.app.service.DefaultMsgService;
import cz.todo_project.app.service.TaskService;

@Component
@ManagedBean
@ViewScoped
public class TestJsfBean implements InitializingBean, DisposableBean {
    @Autowired
    private DefaultMsgService msgService;
    
    @Autowired
    private TaskService taskService;
    
    private List<TaskDTO> tasks = new ArrayList<>();
    private TaskDTO task;
    private TaskDTO newTask;
    
    
    public void init() {
    	tasks = taskService.getAll();
    	newTask = new TaskDTO();
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
	}
	
	public void saveTask() {
		Long guess = newTask.getGuess() != null ? newTask.getGuess() : 0; 
		Double temp = guess.doubleValue() * 1.2;
		newTask.setReal(temp.longValue());
		taskService.create(newTask);
	}
	
	public void deleteTask(Long id) {
		taskService.delete(id);
	}
	

	public void onRowEdit(RowEditEvent event) {
		    task  = (TaskDTO) event.getObject();
	        FacesMessage msg = new FacesMessage("Car Edited", task.getId() + "");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        taskService.update(task);
	        task = null;
	 }
	     
	 public void onRowCancel(RowEditEvent event) {
		 	task  = (TaskDTO) event.getObject();
	        FacesMessage msg = new FacesMessage("Edit Cancelled", task.getId() + "");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        task = null;

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
    

	//replacement @PreDestroy
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}




    
    
}