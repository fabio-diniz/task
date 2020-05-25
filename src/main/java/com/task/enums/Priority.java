package com.task.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public enum Priority {
	LOW (0),
	MEDIUM (1),
	HIGH (2);

	private int priority;
}
