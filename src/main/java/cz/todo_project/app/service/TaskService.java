package cz.todo_project.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.todo_project.app.dao.TaskDAOImpl;
import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.entity.Task;


@Service
public class TaskService {
	
	@Autowired
	private TaskDAOImpl taskDao;
		
	@Autowired
	private UserService userService;
	
	@Transactional
	public void create(TaskDTO entity) {
	 Task domain = transform(entity);
	 taskDao.create(domain);
	}

	@Transactional
	public void update(TaskDTO entity) {
		 Task domain = transform(entity);
		 taskDao.update(domain);
	}

	@Transactional
	public void delete(Long id) {
		 taskDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<TaskDTO> getAll() {
	  	List<Task> tasks = taskDao.getAll();
	  	List<TaskDTO> taskRes = new ArrayList<>();
	  	for (var task  : tasks) {
	  		TaskDTO tsk = transform(task);
	  		taskRes.add(tsk);
	  	}
	  	return taskRes;
	}
	
	
	@Transactional(readOnly = true)
	public List<TaskDTO> getByUsername(String email) {
		List<Task> tasks = taskDao.getByUsername(email);
		List<TaskDTO> taskRes = new ArrayList<>();
	  	for (var task  : tasks) {
	  		TaskDTO tsk = transform(task);
	  		taskRes.add(tsk);
	  	}
	  	return taskRes;
	}

	@Transactional(readOnly = true)
	public TaskDTO get(Long id) {
		 Task domain = taskDao.get(id);
		 return transform(domain);
	}
	
	public TaskDTO transform(Task from) {
		if (from == null) {
			return null;
		}
		TaskDTO to =new TaskDTO();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setFrom(from.getGuess());
		to.setPriority(from.getPriority());
		to.setTo(from.getReal());
		to.setUser(userService.transform(from.getUser()));
		return to;
	}
	
	public Task transform(TaskDTO from) {
		if (from == null) {
			return null;
		}
		Task to =new Task();
		to.setId(from.getId());
		to.setName(from.getName());
		to.setGuess(from.getFrom());
		to.setPriority(from.getPriority());
		to.setReal(from.getTo());
		to.setUser(userService.transform(from.getUser()));
		return to;
	}
	
	

}
