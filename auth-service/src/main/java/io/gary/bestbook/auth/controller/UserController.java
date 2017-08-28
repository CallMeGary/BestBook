package io.gary.bestbook.auth.controller;

import io.gary.bestbook.auth.EntityMapper;
import io.gary.bestbook.auth.domain.User;
import io.gary.bestbook.auth.domain.UserDto;
import io.gary.bestbook.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private EntityMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping
    @RequestMapping(method = GET)
    @PreAuthorize("#oauth2.hasScope('server')")
    Collection<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::toDto).collect(toList());
    }

    @RequestMapping(value = "/current", method = GET)
    Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = POST)
    UserDto registerUser(@Valid @RequestBody UserDto userDto) {

        User newUser = mapper.fromDto(userDto);
        newUser.setRegisteredAt(LocalDateTime.now());

        return mapper.toDto(userService.createUser(newUser));
    }
}
