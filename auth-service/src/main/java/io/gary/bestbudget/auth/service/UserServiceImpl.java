package io.gary.bestbudget.auth.service;

import io.gary.bestbudget.auth.domain.User;
import io.gary.bestbudget.auth.errors.UserAlreadyExistsException;
import io.gary.bestbudget.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    public void create(User user) {

        Optional<User> existing = repository.findByUsername(user.getUsername());

        if (existing.isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("New user has been created: {}", user.getUsername());
    }
}
