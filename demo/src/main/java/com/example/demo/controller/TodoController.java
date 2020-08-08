package com.example.demo.controller;

import com.example.demo.domain.Todo;
import com.example.demo.request.TodoAddRequest;
import com.example.demo.request.TodoUpdateRequest;
import com.example.demo.response.TodoResponse;
import com.example.demo.service.TodoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public ResponseEntity<TodoResponse> getAll() {
        Iterable<Todo> todos = todoService.findAll();
        TodoResponse todoResponse = TodoResponse.builder().todos(todos).build();
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> save(@RequestBody TodoAddRequest request) {
        todoService.save(request.getTodoTitle());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{todoId}")
    public ResponseEntity<HttpStatus> update(@PathVariable Long todoId, @RequestBody TodoUpdateRequest request) {
        todoService.update(request.getTodoId(), request.getTodoTitle(), request.getFinished());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}