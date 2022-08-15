package com.nongboo.flowery.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class TodoDTO {
    private long id;
    private String content;
    private int progress;
    private String date;
}
