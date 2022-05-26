package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.UserForm;
import com.example.delivery.models.User;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @GetMapping("/{id}")
    public OkResponse<User> getUserById(@PathVariable Long id) {
        return OkResponse.of(userService.getUserById(id));
    }

    @PostMapping
    public OkResponse<String> crateUser(@RequestBody UserForm form) {
        userService.addUser(User.fromForm(form));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
