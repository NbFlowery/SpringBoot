package com.nongboo.flowery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    private int progress;

    // 연관 관계 메서드
    public void setPost(Post post){
        this.post = post;
        post.getTodoList().add(this);
    }

    public static Todo createTodo(Post post, String content){
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setPost(post);

        return todo;
    }


    // 삭제 로직직
    public void delete(){
        this.post.getTodoList().remove(this);
    }




}
