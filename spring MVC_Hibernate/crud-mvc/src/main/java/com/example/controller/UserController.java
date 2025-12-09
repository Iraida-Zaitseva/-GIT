package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getlistUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        System.out.println("=== DEBUG saveUser ===");
        System.out.println("User ID: " + user.getId());
        System.out.println("User name: '" + user.getName() + "' (length: " + (user.getName() != null ? user.getName().length() : "null") + ")");
        System.out.println("User email: '" + user.getEmail() + "'");
        System.out.println("BindingResult hasErrors: " + result.hasErrors());
        if (result.hasErrors()) {
            System.out.println("Validation errors:");
            result.getAllErrors().forEach(error -> System.out.println(" - " + error.getDefaultMessage()));
            return "user-form";
        }
        userService.saveUser(user);
        System.out.println("User saved successfully!");
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-form";
        } else {
            return "error";
        }
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-form";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "Произошла ошибка: " + e.getMessage());
        return "error";
    }
}
