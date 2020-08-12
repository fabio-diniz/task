package com.task;

import com.task.dto.TaskDTO;
import com.task.enums.Priority;
import com.task.exception.TaskNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
	
	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepositoryMock;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getTasksNoTaskAdded() {
		
		List<Task> tasks = new ArrayList<>();
		
		taskRepositoryMockFindAll(tasks);
		
		taskService = new TaskService(taskRepositoryMock);
		
		List<TaskDTO> tasksRetuned = taskService.getTasks();
		
		assertNotNull(tasksRetuned);
		assertTrue(tasksRetuned.isEmpty());
	}
	
	@Test
	public void getTasksActiveTask() {
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(createTask(1l, "test", null, true));
		
		taskRepositoryMockFindAll(tasks);
		
		taskService = new TaskService(taskRepositoryMock);
		
		List<TaskDTO> tasksRetuned = taskService.getTasks();
		
		assertTrue(!tasksRetuned.isEmpty());
		assertEquals(tasksRetuned.size(), 1);
		assertTrue(TaskDTO.taskDTOToTask(tasksRetuned.get(0), true).equals(tasks.get(0)));
	}
	
	@Test
	public void getTasksNotActiveTask() {
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(createTask(1l, "test", null, false));
		
		taskRepositoryMockFindAll(tasks);
		
		taskService = new TaskService(taskRepositoryMock);
		
		List<TaskDTO> tasksRetuned = taskService.getTasks();
		
		assertTrue(tasksRetuned.isEmpty());
	}
	
	@Test
	public void getTaskActiveTask() {
		
		Task task = createTask(1l, "test", null, true);
		
		taskRepositoryMockFindById(task);
		
		taskService = new TaskService(taskRepositoryMock);
		
		TaskDTO taskDTO = taskService.getTask(task.getId());
		
		assertNotNull(taskDTO);
	}
	
	public void getTaskNotActiveTask() {
		
		Task task = createTask(1l, "test", null, false);
		
		taskRepositoryMockFindById(task);
		
		taskService = new TaskService(taskRepositoryMock);
		
		Assertions.assertThrows(TaskNotFoundException.class, () -> { taskService.getTask(task.getId());});
			
	}
	
	public void getTaskNonExistentTask() {
		
		taskRepositoryMockFindById(null);
		
		taskService = new TaskService(taskRepositoryMock);
		
		Assertions.assertThrows(TaskNotFoundException.class, () -> { taskService.getTask(1l);});
			
	}
	
	private void taskRepositoryMockFindAll(List<Task> tasks) {
		taskRepositoryMock = Mockito.mock(TaskRepository.class);
		Mockito.when(taskRepositoryMock.findAll()).thenReturn(tasks);
	}
	
	private void taskRepositoryMockFindById(Task task) {
		taskRepositoryMock = Mockito.mock(TaskRepository.class);
		if (task != null) {
			Optional<Task> taskOptional = Optional.of(task);
			Mockito.when(taskRepositoryMock.findById(task.getId())).thenReturn(taskOptional);
		} else {
			Mockito.when(taskRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		}
	}
	
	private Task createTask(Long id, String description, Priority priority, boolean active) {
		return Task.builder()
				.id(id)
				.description(description)
				.priority(priority)
				.active(active)
				.build();
	}
}
