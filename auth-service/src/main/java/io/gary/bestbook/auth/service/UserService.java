package io.gary.bestbook.auth.service;

import io.gary.bestbook.auth.domain.User;
import io.gary.bestbook.auth.errors.UserAlreadyExistsException;
import io.gary.bestbook.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    public Collection<User> getAllUsers() {
        return repository.findAll();
    }

    public User createUser(User user) {

        Optional<User> existing = repository.findByUsername(user.getUsername());

        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        user.setPassword(encoder.encode(user.getPassword()));

        User created = repository.save(user);

        log.info("New user has been created: {}", user.getUsername());
        return created;
    }
}
