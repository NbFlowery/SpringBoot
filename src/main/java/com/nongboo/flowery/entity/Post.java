package com.nongboo.flowery.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Todo> todoList = new ArrayList();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bed_id")
    private FlowerBed flowerBed;


    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    public void setFlowerBed(FlowerBed flowerBed){
        this.flowerBed = flowerBed;
        flowerBed.getPosts().add(this);
    }
    public static Post createPost(String date, User user){
        Post post = new Post();
        post.setDate(date);
        post.setUser(user);

        return post;
    }

}
