package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import com.example.demo.domain.Todo;
import com.example.demo.repository.TodoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    // それぞれのテスト前に毎回全部消し飛ばす
    @BeforeEach
    void setup() {
        todoRepository.deleteAll();
    }

    @Test
    void canSaveAndUpdateTodo() {
        String todoTitle = "todotest";
        boolean finished = false;
        LocalDateTime createdAt = LocalDateTime.now();

        // 新規にinsert、todoIdはPostgres側で振るようnullに
        Todo todo = new Todo(null, todoTitle, finished, createdAt);
        Todo savedTodo = todoRepository.save(todo);
        assertThat(savedTodo.getTodoId()).isNotNull();
        // 完了扱いにしてupdate
        todo = todo.withFinished(!todo.getFinished());
        savedTodo = todoRepository.save(todo);
        assertThat(savedTodo.getFinished()).isTrue();
    }

    @Test
    void canCountFinishedTodos() {
        String todoTitle = "todotest";
        boolean finished = true;
        LocalDateTime createdAt = LocalDateTime.now();

        for (int i = 0; i < 10; i++) {
            Todo todo = new Todo(null, todoTitle, finished, createdAt);
            todoRepository.save(todo);
        }
        assertThat(todoRepository.countByFinished(finished)).isEqualTo(10L);
        assertThat(todoRepository.countByFinished(!finished)).isZero();
    }

}