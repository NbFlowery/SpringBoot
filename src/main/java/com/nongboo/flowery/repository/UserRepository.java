package com.nongboo.flowery.repository;

import com.nongboo.flowery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(long id);

    @Query(value = "SELECT user_id FROM user where email = :email", nativeQuery = true)
    Long findUserIdByUserEmail(String email);

}