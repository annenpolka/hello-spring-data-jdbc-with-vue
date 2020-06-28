package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data // Getter,Setter,equals,hashCode,RequiredArgsConstructorをLombokがやる
@AllArgsConstructor // 全引数のコンストラクタをやる
@With // 既存インスタンスの値を変えた新しいインスタンスを返すWithXXメソッドを一通りやる
public class Todo {
    @Id
    private Long todoId;
    private String todoTitle;
    private Boolean finished;
    private final LocalDateTime createdAt = LocalDateTime.now();
}