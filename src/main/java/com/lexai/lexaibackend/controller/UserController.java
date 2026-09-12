package com.lexai.lexaibackend.controller;

import com.lexai.lexaibackend.model.User;
import com.lexai.lexaibackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name="Users " ,description = "User  management endpoints")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User saved = userService.registerUser(user);
//        return ResponseEntity.ok(saved);
//    }

    @Operation(summary = "Get user by id" , description = "Returns all user details by User database id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}