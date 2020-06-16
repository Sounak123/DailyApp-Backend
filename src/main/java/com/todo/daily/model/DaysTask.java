package com.todo.daily.model;

import java.time.LocalDate;
import java.util.List;

public class DaysTask {
	private LocalDate date;
	private List<Task> tasks;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	

}
