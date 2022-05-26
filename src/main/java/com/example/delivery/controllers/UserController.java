package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.UserForm;
import com.example.delivery.models.User;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.UserService;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @GetMapping
    public OkResponse<ImmutableList<User>> getUsers() {
        return OkResponse.of(ImmutableList.copyOf(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public OkResponse<User> getUserById(@PathVariable Long id) {
        return OkResponse.of(userService.getUserById(id));
    }

    @PostMapping
    public OkResponse<String> crateUser(@RequestBody UserForm form) {
        userService.addUser(User.fromForm(null, form));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @PutMapping("/{id}")
    public OkResponse<String> updateUser(
            @PathVariable Long id,
            @RequestBody UserForm from
    ) {
        userService.updateUser(User.fromForm(id, from));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public OkResponse<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
