package com.example.salary_management.controller;

import com.example.salary_management.model.User;
import com.example.salary_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Trang chủ — hiển thị danh sách + form
    @GetMapping
    public String listUsers(Model model,
                            @RequestParam(required = false) String keyword) {
        List<User> users;
        if (keyword != null && !keyword.isEmpty()) {
            users = userService.searchUsers(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            users = userService.getAllUsers();
        }
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "index";
    }

    // Thêm mới user
    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") User user,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "index";
        }
        String message = userService.saveUser(user);
        model.addAttribute("message", message);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "index";
    }

    // Load form Edit
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("editMode", true);
        return "index";
    }

    // Cập nhật user
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("editMode", true);
            return "index";
        }
        userService.updateUser(user);
        model.addAttribute("message", "User updated successfully");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "index";
    }

    // Xóa user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}