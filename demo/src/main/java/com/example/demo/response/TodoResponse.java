package com.example.demo.response;

import com.example.demo.domain.Todo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoResponse {

    private Iterable<Todo> todos;
}