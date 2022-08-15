package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.DTO.TodoDTO;
import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.entity.User;
import com.nongboo.flowery.repository.PostRepository;
import com.nongboo.flowery.repository.TodoRepository;
import com.nongboo.flowery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Todo getTodo(long id) {
        Todo todo = todoRepository.findTodoById(id);
        return todo;
    }

    @Override
    public List<TodoDTO> getTodoByUserIdAndDate(long userId, String date) {
        List<TodoDTO> todoList = new ArrayList<>();
        List<Post> postList = postRepository.findPostByUserIdAndMonth(userId, date);

        System.out.println(date);

        for(Post post : postList){
            List<Todo> todos = post.getTodoList();
            for(Todo todo : todos) {
                todoList.add(new TodoDTO(todo.getId(), todo.getContent(), todo.getProgress(), todo.getPost().getDate()));
            }
        }

        return todoList;
    }


    @Override
    @Transactional
    public Todo createTodo(long userId, String date, String content) {

        User user = userRepository.findUserById(userId);
        Optional<Post> postOptional = postRepository.findPostByUserIdAndDate(userId, date);

        Post post = postOptional.orElseGet(() -> Post.createPost(date, user));;

        Todo todo = Todo.createTodo(post, content);

        todoRepository.save(todo);

        return todo;
    }

    @Override
    @Transactional
    public Todo updateTodo(long userId, TodoDTO todoDTO) {

        Todo todo = todoRepository.findTodoById(todoDTO.getId());
        User user = userRepository.findUserById(userId);

        Optional<Post> postOptional = postRepository.findPostByUserIdAndDate(userId, todoDTO.getDate());

        Post post = postOptional.orElseGet(() -> Post.createPost(todoDTO.getDate(), user));
        postRepository.save(post);

        todo.setPost(post);
        todo.setContent(todoDTO.getContent());
        todo.setProgress(todoDTO.getProgress());

        return todoRepository.save(todo);
    }

    @Override
    @Transactional
    public long deleteTodo(long id) {
        Todo todo = todoRepository.findTodoById(id);
        todo.delete();
        todoRepository.delete(todo);
        return id;
    }



}
