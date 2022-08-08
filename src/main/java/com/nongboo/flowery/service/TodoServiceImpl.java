package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.entity.User;
import com.nongboo.flowery.repository.PostRepository;
import com.nongboo.flowery.repository.TodoRepository;
import com.nongboo.flowery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Todo updateTodo(Todo todo) {
        return null;
    }

    @Override
    public Todo deleteTodo(Todo todo) {
        return null;
    }
}
