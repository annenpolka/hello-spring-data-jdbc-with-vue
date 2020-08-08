package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.repository.TodoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Iterable<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo save(String todoTitle) {
        return todoRepository.save(Todo.newTodo(todoTitle));
    }

    public Todo update(Long todoId, String todoTitle, Boolean finished) {
        Todo todo = todoRepository.findById(todoId).get();
        return todoRepository.save(todo.withTodoId(todoId).withTodoTitle(todoTitle).withFinished(finished));

    }

    public void delete(Long todoId) {
        todoRepository.findById(todoId).ifPresent(todo -> todoRepository.delete(todo));
    }
}