package com.backend.social.repositories;

import com.backend.social.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByEmail(String email);
}
