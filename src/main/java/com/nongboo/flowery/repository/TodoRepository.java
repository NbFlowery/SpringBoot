package com.nongboo.flowery.repository;

import com.nongboo.flowery.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findTodoById(Long toto);

}