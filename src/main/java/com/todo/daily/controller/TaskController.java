package com.todo.daily.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.daily.model.DaysTask;
import com.todo.daily.model.Task;
import com.todo.daily.service.TaskService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("daily-task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/add-task")
	public void addNewTask(@RequestBody Task task) throws ValidationException {
		System.out.println("Start date time:"+task.getStart().toString());
		taskService.addNewTask(task);
	}
	
	@PostMapping("/update-task")
	public void updateTask(@RequestBody Task task) throws ValidationException {
		taskService.updateTask(task);
	}
	
	@PostMapping("/get-task")
	public @ResponseBody List<DaysTask> getTask(@RequestBody Task task) {
		LocalDateTime from = task.getStart().toLocalDate().atStartOfDay();
		LocalDateTime to = getFromAndTO(task, from);
		
		return taskService.getTaskBetweenDates(from, to);
	}
	
	private static LocalDateTime getFromAndTO(Task task, LocalDateTime from) {
		LocalDate startDate = task.getStart().toLocalDate();
		from = startDate.atStartOfDay();
		if(task.getEnd()!= null) {
			return task.getEnd().toLocalDate().atTime(23, 59, 59); 
		}else {
			return startDate.plusDays(6).atTime(23, 59, 59);
		}
	}
	
	
	
	
}
