package cz.todolist.app.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.todolist.app.dto.TaskDTO;
import cz.todolist.app.service.DefaultMsgService;
import cz.todolist.app.service.TaskService;

@Component
@ManagedBean
@RequestScoped
public class TestJsfBean implements InitializingBean, DisposableBean {
    @Autowired
    private DefaultMsgService msgService;
    
    @Autowired
    private TaskService taskService;
    
    private List<TaskDTO> tasks = new ArrayList<>();
    private TaskDTO task;
    
    
    public void init() {
    	tasks = taskService.getAll();
    } 
    
    public List<TaskDTO> getTasks() {
		return tasks;
	}


	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
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