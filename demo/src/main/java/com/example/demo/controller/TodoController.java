package com.example.demo.controller;

import com.example.demo.domain.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.response.TodoResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public ResponseEntity<TodoResponse> getAll() {
        Iterable<Todo> todos = todoRepository.findAll();
        TodoResponse todoResponse = TodoResponse.builder().todos(todos).build();
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

}