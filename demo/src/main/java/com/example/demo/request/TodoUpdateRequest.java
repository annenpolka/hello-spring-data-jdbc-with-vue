package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateRequest {

    private Long todoId;
    private String todoTitle;
    private Boolean finished;

}