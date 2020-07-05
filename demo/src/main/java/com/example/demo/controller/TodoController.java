package com.example.demo.controller;

import com.example.demo.domain.Todo;
import com.example.demo.repository.TodoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;

    TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
   }

    @GetMapping("/")
    public Iterable<Todo> getAll() {
        return todoRepository.findAll();
    }

}