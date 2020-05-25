package com.task.dto;

import javax.validation.constraints.Size;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.task.Task;
import com.task.enums.Priority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class TaskUpdateDTO {
	
	private Long id;
	
	@Size(max = 255)
	private String description;
	
	private Priority priority;
		
	public static TaskUpdateDTO taskToTaskUpdateDTO(Task task) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(task, TaskUpdateDTO.class);
	}
	
	public static Task taskUpdateOToTask(TaskUpdateDTO taskUpdateDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(taskUpdateDTO, Task.class);
	}
}
