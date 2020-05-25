package com.task.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.task.Task;
import com.task.enums.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(Include.NON_NULL)
public class TaskDTO {
	
	private Long id;
	
	@Size(max = 255)
	@NotBlank(message = "description is mandatory")
	private String description;
	
	private Priority priority;
		
	public static TaskDTO taskToTaskDTO(Task task) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(task, TaskDTO.class);
	}
	
	public static Task taskDTOToTask(TaskDTO taskDTO, boolean active) {
		ModelMapper modelMapper = new ModelMapper();
		Task task = modelMapper.map(taskDTO, Task.class);
		task.setActive(active);
		return task;
	}
}
