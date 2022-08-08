package com.nongboo.flowery.controller;

import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.Todo;
import com.nongboo.flowery.service.PostService;
import com.nongboo.flowery.service.UserService;
import com.nongboo.flowery.util.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/v1/post")
    public Header<CreatePostResponse> createPostV1(@RequestBody CreatePostRequest request){
        try{
            Post post = new Post();
            post.setUser(userService.findOne(request.id));
            post.setDate(request.date);

            Post result = postService.createPost(post);
            return Header.SUCCESS(new CreatePostResponse(result.getId()));
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }

    @GetMapping("/v1/post/{id}")
    public Header<PostDTO> getPostV1(@PathVariable long id){
        try{
            Post post = postService.findOne(id);

            return Header.SUCCESS(new PostDTO(post.getId(), post.getTodoList(), post.getDate()));
        }catch (Exception e){
            return Header.FAIL(e);
        }

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
