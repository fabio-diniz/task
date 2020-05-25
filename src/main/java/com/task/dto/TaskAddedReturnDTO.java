package com.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor
public class TaskAddedReturnDTO {

	@JsonProperty("taskId")
	private long id;
}
