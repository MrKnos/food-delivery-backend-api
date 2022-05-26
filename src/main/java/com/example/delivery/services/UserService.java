package com.example.delivery.services;

import com.example.delivery.entities.UserEntity;
import com.example.delivery.exceptions.data_not_found.UserNotFoundException;
import com.example.delivery.models.User;
import com.example.delivery.reopositories.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserRepository userRepository;

    public ImmutableList<User> getAllUsers() {
        return ImmutableList.copyOf(
                userRepository.findAll()
                        .stream().map(User::fromEntity)
                        .toList()
        );
    }

    public User getUserById(Long id) {
        return User.fromEntity(getUserEntityById(id));
    }

    UserEntity getUserEntityById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void updateUser(User user) {
        final UserEntity userEntity = getUserEntityById(user.id().orElse(null));
        userEntity.updateFrom(user);

        userRepository.save(userEntity);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(UserEntity.fromModel(user));
    }
}
