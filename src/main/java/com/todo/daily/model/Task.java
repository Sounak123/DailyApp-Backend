package com.todo.daily.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String task;
	private LocalDateTime start;
	private LocalDateTime end;
	@Enumerated(EnumType.STRING)
	private TaskStatusEnum status;
	@Enumerated(EnumType.STRING)
	private TaskTypeEnum taskType;
	
	protected Task() {}
	
	public Task(String task, LocalDateTime start, LocalDateTime end, TaskStatusEnum status, TaskTypeEnum taskType) {
		this.task = task;
		this.start = start;
		this.end = end;
		this.status = status;
		this.taskType = taskType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public TaskStatusEnum getStatus() {
		return status;
	}
	public void setStatus(TaskStatusEnum status) {
		this.status = status;
	}
	public TaskTypeEnum getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskTypeEnum taskType) {
		this.taskType = taskType;
	}
	
	

}
