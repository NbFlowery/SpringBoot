package com.nongboo.flowery.controller;

import com.nongboo.flowery.entity.DTO.TodoDTO;
import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.service.TodoService;
import com.nongboo.flowery.util.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("v1/todo/{id}")
    public Header<TodoDTO> getTodoV1(@PathVariable long id){
        try{
            Todo todo = todoService.getTodo(id);
            if(todo == null)
                return Header.FAIL(new Exception());
            else
                return Header.SUCCESS(new TodoDTO(todo.getId(), todo.getContent(), todo.getProgress(), todo.getPost().getDate()));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }

    @PostMapping("/v1/todo")
    public Header<CreateTodoResponse> createTodoV1(@RequestBody CreateTodoRequest request){
        try{
            Todo todo = todoService.createTodo(request.userId, request.date, request.content);
            return Header.SUCCESS(new CreateTodoResponse(todo.getId(), todo.getContent()));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }

    @PutMapping("/v1/todo")
    public Header<UpdateTodoResponse> updateTodoV1(@RequestBody UpdateTodoRequest request){
        try{
            Todo todo = todoService.updateTodo(request.userId, request.todoDTO);
            return Header.SUCCESS(new UpdateTodoResponse(new TodoDTO(todo.getId(), todo.getContent(), todo.getProgress(), todo.getPost().getDate())));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }

    @DeleteMapping("/v1/todo/{id}")
    public Header<DeleteTodoResponse> deleteTodoV1(@PathVariable long id){
        try{
            long todoId = todoService.deleteTodo(id);
            return Header.SUCCESS(new DeleteTodoResponse(todoId));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }

    @Data
    static class CreateTodoRequest{
        private long userId;
        private String date;
        private String content;
        private int progress;
    }

    @Data
    @AllArgsConstructor
    static class CreateTodoResponse{
        private long id;
        private String content;
    }

    @Data
    static class UpdateTodoRequest{
        private long userId;
        private TodoDTO todoDTO;
    }

    @Data
    @AllArgsConstructor
    static class UpdateTodoResponse{
        private TodoDTO todoDTO;
    }

    @Data
    @AllArgsConstructor
    static class DeleteTodoResponse{
        private long id;
    }

}
