package com.ex.artion.artion.user.respository;

import com.ex.artion.artion.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM user_entity a " +
            "WHERE a.kakao_pk = :kakao_pk "
            ,nativeQuery = true)
    Optional<UserEntity> findByKakao_pk(String kakao_pk);
}