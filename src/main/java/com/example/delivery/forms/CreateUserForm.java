package com.example.delivery.forms;

import java.util.List;

public record CreateUserForm(
        String username,
        String password,
        List<String> roles
) {
    public static CreateUserForm fromMock() {
        return new CreateUserForm(
                "Knos",
                "1234",
                List.of("USER")
        );
    }
}
