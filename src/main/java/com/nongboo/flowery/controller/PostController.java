package com.nongboo.flowery.controller;

import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.service.PostService;
import com.nongboo.flowery.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/v1")
    public CreatePostResponse createPostV1(@RequestBody CreatePostRequest request){
        Post post = new Post();
        post.setUser(userService.findOne(request.id));
        post.setDate(request.date);

        Post result = postService.createPost(post);
        return new CreatePostResponse(result.getId());
    }

    @GetMapping("/v1/{id}")
    public PostDTO getPostV1(@PathVariable long id){
        Post post = postService.findOne(id);

        return new PostDTO(post.getId(), post.getTodoList(), post.getDate());
    }

    @Data
    static class CreatePostRequest{
        private long id;
        private String date;
    }

    @Data
    @AllArgsConstructor
    static class CreatePostResponse{
        private long id;
    }

    @Data
    @AllArgsConstructor
    static class PostDTO{
        private long id;
        private List<Todo> todoList;
        private String date;
    }

}
