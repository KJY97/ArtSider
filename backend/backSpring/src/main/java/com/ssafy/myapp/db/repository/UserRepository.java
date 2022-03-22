package com.ssafy.myapp.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.myapp.db.entity.User;

import java.util.Optional;

/**
 * ���� �� ���� ��� ���� ������ ���� JPA Query Method �������̽� ����.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // �Ʒ��� ����, Query Method �������̽�(��ȯ��, �޼ҵ��, ����) ���Ǹ� �ϸ� �ڵ����� Query Method ������.
    Optional<User> findUserByEmail(String userEmail);
    Optional<User> findUserById(Long id);
}