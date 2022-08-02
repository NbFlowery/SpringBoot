package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.User;
import com.nongboo.flowery.repository.PostRepository;
import com.nongboo.flowery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post findOne(long id){

        Optional<Post> post = postRepository.findById(id);

        return post.orElse(null);
    }
}
