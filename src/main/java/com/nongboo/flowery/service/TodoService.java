package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.Todo;

public interface TodoService {

    Todo createTodo(long userId, String date, String content);

    Todo updateTodo(Todo todo);

    Todo deleteTodo(Todo todo);


}
