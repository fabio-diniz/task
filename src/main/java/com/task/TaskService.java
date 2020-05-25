package com.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.task.dto.TaskAddedReturnDTO;
import com.task.dto.TaskDTO;
import com.task.dto.TaskUpdateDTO;
import com.task.exception.TaskNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;
	
	public List<TaskDTO> getTasks() {
		return taskRepository.findAll().stream()
				.filter(Task::isActive)
				.map(TaskDTO::taskToTaskDTO)
				.collect(Collectors.toList());
	}
	
	public TaskDTO getTask(long id) {
		return TaskDTO.taskToTaskDTO(findActiveTask(id));
	}
	
	public TaskAddedReturnDTO createTask(TaskDTO taskDTO) {
		Task task = TaskDTO.taskDTOToTask(taskDTO, true);

		taskRepository.save(task);
		return new TaskAddedReturnDTO(task.getId());
	}
	
	public void updateTask(long id, TaskUpdateDTO taskDTO) {
		Task task = findActiveTask(id);
		
		if (task.getDescription() != null) {
			task.setDescription(taskDTO.getDescription());
		}
		
		if (taskDTO.getPriority() != null) {
			task.setPriority(taskDTO.getPriority());
		}
		
		taskRepository.save(task);
	}
	
	public void deactivateTask(long id) {
		Task task = findActiveTask(id);
		task.setActive(false);
		taskRepository.save(task);
	}
	
	private Task findActiveTask(Long id) {
		return taskRepository.findById(id)
				.filter(Task::isActive)
				.orElseThrow(() -> new TaskNotFoundException(id));
	}
}
