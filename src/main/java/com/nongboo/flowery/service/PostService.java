package com.nongboo.flowery.service;

import com.nongboo.flowery.entity.Post;
import org.springframework.stereotype.Service;


public interface PostService {

    Post createPost(Post post);

    Post findOne(long id);

}
