package com.example.delivery.services;

import com.example.delivery.entities.RoleEntity;
import com.example.delivery.entities.UserEntity;
import com.example.delivery.exceptions.data_not_found.DataNotFoundException;
import com.example.delivery.exceptions.data_not_found.UserNotFoundException;
import com.example.delivery.forms.CreateUserForm;
import com.example.delivery.models.Role;
import com.example.delivery.models.User;
import com.example.delivery.reopositories.RoleRepository;
import com.example.delivery.reopositories.UserRepository;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

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
        userEntity.setUsername(user.username());

        userRepository.save(userEntity);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public User createUser(CreateUserForm form) {
        final UserEntity entity = UserEntity.fromForm(form);
        entity.setPassword(passwordEncoder.encode(form.password()));

        form.roles().forEach(role -> {
            final RoleEntity roleEntity = getRoleEntity(new Role(role));
            entity.addRole(roleEntity);
        });

        return User.fromEntity(userRepository.save(entity));
    }

    public RoleEntity getRoleEntity(Role role) {
        return roleRepository
                .findByRoleName(role.name())
                .orElseThrow(() -> new DataNotFoundException(RoleEntity.class, role.name()));
    }

    public void createRole(String roleName) {
        final Role role = Role.fromName(roleName);

        if (!roleRepository.existsByRoleName(role.name())) {
            roleRepository.save(RoleEntity.fromModel(role));
        }
    }
}
