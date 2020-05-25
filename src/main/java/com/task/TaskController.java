package com.task;

import com.task.dto.TaskAddedReturnDTO;
import com.task.dto.TaskDTO;
import com.task.dto.TaskUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class TaskController {

	private final TaskService taskService;

	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@GetMapping(path = "/tasks", produces = "application/json")
	public ResponseEntity<List<TaskDTO>> getTasks() {
		List<TaskDTO> tasks = taskService.getTasks();
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping(path = "/tasks/{id}", produces = "application/json")
	public ResponseEntity<TaskDTO> getTask(@PathVariable("id") long id) {
		TaskDTO taskDTO = taskService.getTask(id);
		return ResponseEntity.ok(taskDTO);
	}
	
	@PostMapping(path = "/tasks", consumes = "application/json")
	public TaskAddedReturnDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {
		return taskService.createTask(taskDTO);
	}
	
	@PutMapping(path = "/tasks/{id}", consumes = "application/json")
	public void updateTask(@PathVariable long id ,@Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
		taskService.updateTask(id, taskUpdateDTO);
	}
	
	@DeleteMapping(path = "/tasks/{id}")
	public void deleteTask(@PathVariable("id") long id) {
		taskService.deactivateTask(id);
	}
}
