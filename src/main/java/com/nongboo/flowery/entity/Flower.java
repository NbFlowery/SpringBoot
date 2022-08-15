package com.nongboo.flowery.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Flower {

    private String korName; // 한글 이름
    private String engName; // 영어 이름

    private String explanation; // 설명

    private String flowerImage; // 꽃 이미지
}
