package com.example.springboot.repository;

import com.example.springboot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findByEmail(String email);

    Optional<UserEntity> findOneByEmail( String email );
}