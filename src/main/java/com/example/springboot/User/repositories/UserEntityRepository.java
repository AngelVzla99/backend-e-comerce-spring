package com.example.springboot.User.repositories;

import com.example.springboot.User.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findByEmail(String email);

    Optional<UserEntity> findOneByEmail( String email );
}