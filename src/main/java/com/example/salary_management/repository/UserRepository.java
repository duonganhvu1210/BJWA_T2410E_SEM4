package com.example.salary_management.repository;

import com.example.salary_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Dùng để kiểm tra trùng tên khi tạo mới
    Optional<User> findByName(String name);

    // Dùng cho chức năng Search
    List<User> findByNameContainingIgnoreCase(String keyword);
}