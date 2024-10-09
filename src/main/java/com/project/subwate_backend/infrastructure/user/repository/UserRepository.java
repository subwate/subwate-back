package com.project.subwate_backend.infrastructure.user.repository;

import com.project.subwate_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    <S extends User> S save(S user);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    User findByEmail(String email);
}
