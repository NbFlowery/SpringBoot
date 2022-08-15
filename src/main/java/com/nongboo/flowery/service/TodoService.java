package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.DTO.TodoDTO;
import com.nongboo.flowery.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo getTodo(long id);

    List<Todo> getAllTodoByUserId(long userId);

    Todo createTodo(long userId, String date, String content);

    Todo updateTodo(long userId, TodoDTO todo);

    long deleteTodo(long id);


}
