package com.example.salary_management.service;

import com.example.salary_management.model.User;
import com.example.salary_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Lấy tất cả user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Tìm theo ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Tạo mới — kiểm tra trùng tên
    public String saveUser(User user) {
        Optional<User> existing = userRepository.findByName(user.getName());
        if (existing.isPresent()) {
            return "Error while creating User: Unable to create. A User with name "
                    + user.getName() + " already exist.";
        }
        userRepository.save(user);
        return "User created successfully";
    }

    // Cập nhật
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // Xóa
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Tìm kiếm theo tên
    public List<User> searchUsers(String keyword) {
        return userRepository.findByNameContainingIgnoreCase(keyword);
    }
}