package com.example.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Item {
    int id;
    LocalDateTime createdAt;
    String title;
    boolean finished;
}
