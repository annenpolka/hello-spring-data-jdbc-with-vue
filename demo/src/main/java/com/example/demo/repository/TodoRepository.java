package com.example.demo.repository;

import com.example.demo.domain.Todo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    // :hogehoge で書いたところに値を@Paramで代入できる
    @Query("SELECT COUNT(*) FROM todo WHERE finished = :finished")
    long countByFinished(@Param("finished") boolean finished);
}