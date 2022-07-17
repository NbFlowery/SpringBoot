package com.nongboo.flowery.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime date; //이건 기간인거면 따로 클래스로 뺴야하는가

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //Category 관계를 어떻게 짜야 할까
    //할일 완료 퍼센트
}
