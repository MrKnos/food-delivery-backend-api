package com.example.delivery.services;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.models.User;
import com.example.delivery.reopositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(UserEntity.fromModel(user));
    }
}
