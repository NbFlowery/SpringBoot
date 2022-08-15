package com.nongboo.flowery.repository;

import com.nongboo.flowery.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select * from post where user_id = :userId and date = :date",nativeQuery = true)
    Optional<Post> findPostByUserIdAndDate(long userId, String date);


    @Query(value = "SELECT * FROM post WHERE user_id = :userId and date like :date% order by date ", nativeQuery = true)
    List<Post> findPostByUserIdAndMonth(long userId, String date);
}