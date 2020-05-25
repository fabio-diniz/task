package com.task.exception;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8048913546445186971L;

	public TaskNotFoundException(Long id) {
		super("Task id not found: " + id);
	}
}
