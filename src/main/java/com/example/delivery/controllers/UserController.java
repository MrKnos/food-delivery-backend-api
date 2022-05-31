package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.CreateUserForm;
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
    public OkResponse<User> createUser(@RequestBody CreateUserForm form) {
        return OkResponse.of(userService.createUser(form));
    }

    @PutMapping("/{id}")
    public OkResponse<String> updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserForm from
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
