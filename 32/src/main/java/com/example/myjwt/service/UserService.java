package com.example.myjwt.service;


import com.example.myjwt.dto.Role;
import com.example.myjwt.dto.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = Stream.of(
                new User("anton", "1234", "Антон", "Иванов", Collections.singleton(Role.USER)),
                new User("ivan", "12345", "Сергей", "Петров", Collections.singleton(Role.ADMIN))
        ).collect(Collectors.toList());
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }
}