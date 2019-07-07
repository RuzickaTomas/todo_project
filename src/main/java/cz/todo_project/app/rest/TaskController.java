package cz.todo_project.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.todo_project.app.dto.TaskDTO;
import cz.todo_project.app.service.TaskService;

@RestController
@RequestMapping("/rest")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskDTO get(@RequestParam Long id) {
		TaskDTO task = service.get(id);
		return task;
	}
	
	@GetMapping(value = "/task/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TaskDTO> getAll(@RequestParam(required = false) Long id) {
		TaskDTO task = null;
		List<TaskDTO> tasks = new ArrayList<>();
		if (id != null) {
			task = service.get(id);
			tasks.add(task);
		} else {
			return service.getAll();
		}
		return tasks;
	}
	
	
	@PostMapping(value = "/task/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskDTO createOrUpdate(@RequestBody TaskDTO task) {
		service.create(task);
		return task;
	}
	
	
	@PostMapping(value = "/test/new", produces = MediaType.TEXT_PLAIN_VALUE)
	public String testPost(@RequestBody(required = false) TaskDTO task) {
		return "POST TEST";
	}

	
	@GetMapping( value ="test", produces = MediaType.TEXT_PLAIN_VALUE)
	public String test() {
		return "TEST";
	}

}
