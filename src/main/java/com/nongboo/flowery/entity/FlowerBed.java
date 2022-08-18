package com.nongboo.flowery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class FlowerBed {

    @Id @GeneratedValue
    @Column(name = "bed_id")
    private long id;

    @OneToMany(mappedBy = "flowerBed", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList();

    @Embedded
    private Flower flower;

    public int getFlowerCount(){ //완성된 꽃 갯수 리턴
        int count = 0;

        for(Post post : posts){
            for(Todo todo : post.getTodoList()){
                if(todo.getProgress() == 100)
                    count++;
            }
        }
        return count;
    }
}
