package com.nongboo.flowery.controller;

import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.service.TodoService;
import com.nongboo.flowery.util.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping("/v1/todo")
    public Header<CreateTodoResponse> createTodoV1(@RequestBody CreateTodoRequest request){
        try{
            Todo todo = todoService.createTodo(request.userId, request.date, request.content);
            return Header.SUCCESS(new CreateTodoResponse(todo.getId(), todo.getContent()));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }


    @Data
    static class CreateTodoRequest{
        private long userId;
        private String date;
        private String content;
    }

    @Data
    @AllArgsConstructor
    static class CreateTodoResponse{
        private long id;
        private String content;
    }

}
