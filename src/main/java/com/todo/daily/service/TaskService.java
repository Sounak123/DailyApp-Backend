package com.todo.daily.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.daily.data.TaskRepository;
import com.todo.daily.model.DaysTask;
import com.todo.daily.model.Task;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void addNewTask(Task task) throws ValidationException {
		if(task.getStart().isAfter(task.getEnd())) {
			throw new ValidationException("End date should be after start date");
		}
		
		taskRepository.save(task);
	}
	
	public List<DaysTask> getTaskBetweenDates(LocalDateTime from, LocalDateTime to){
		List<DaysTask> daysTaskList = new ArrayList<>();
		LocalDate start = from.toLocalDate();
		LocalDate end = to.toLocalDate();
		while(end.isAfter(start) || end.equals(start)) {
			DaysTask daysTask = new DaysTask();
			daysTask.setDate(start);
			daysTask.setTasks(new ArrayList<>());
			daysTaskList.add(daysTask);
			start = start.plusDays(1);
		}
		
		taskRepository.findByEndAfterAndStartBefore(from, to).forEach(task->{
			for(DaysTask daysTask : daysTaskList) {
				if(daysTask.getDate().atStartOfDay().isBefore(task.getEnd()) && daysTask.getDate().atTime(23, 59, 59).isAfter(task.getStart())) {
					daysTask.getTasks().add(task);
				}
			}
		});
		return daysTaskList;
	}
	
	public void updateTask(Task task) throws ValidationException {
		if(task.getId()==0) {
			throw new ValidationException("Please enter Id to update the entry");
		}
		
		if(task.getStart().isAfter(task.getEnd())) {
			throw new ValidationException("End date should be after start date");
		}
		
		taskRepository.save(task);
		
	}

}
