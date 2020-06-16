package com.todo.daily.data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.daily.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findByEndAfterAndStartBefore(LocalDateTime from, LocalDateTime to);

}
