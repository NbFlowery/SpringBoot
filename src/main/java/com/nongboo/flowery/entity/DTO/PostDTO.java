package com.nongboo.flowery.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class PostDTO {
    private long id;
    private List<TodoDTO> todoList;
    private String date;
}
