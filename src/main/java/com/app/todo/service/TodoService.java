package com.app.todo.service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import com.app.todo.entity.Todo;
import com.app.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equals(username);
		return todoRepository.findAll().stream().filter(predicate).toList();
	}

	public void addTodo(String username, String desc, LocalDate targetDate, boolean done) {


		Todo todo = new Todo(username, desc, targetDate, done);
		todoRepository.save(todo);
	}

	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todoRepository.deleteById(id);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		return todoRepository.findById(id).get();
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todoRepository.save(todo);
	}

	public void save(Todo todo) {
		todoRepository.save(todo);
	}
}
