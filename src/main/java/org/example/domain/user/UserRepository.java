package org.example.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //소셜로그인으로 반환되는 값중 처음가입인지 이미 생성된 사용자인지 구분
}
