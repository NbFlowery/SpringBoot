package com.nongboo.flowery.repository;

import com.nongboo.flowery.entity.Post;
import com.nongboo.flowery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post where user_id = :userId and date = :date",nativeQuery = true)
    Optional<Post> findPostByUserIdAndDate(long userId, String date);

}