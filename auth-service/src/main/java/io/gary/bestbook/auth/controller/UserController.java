package io.gary.bestbook.auth.controller;

import io.gary.bestbook.auth.domain.User;
import io.gary.bestbook.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private UserService userService;

//    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = GET)
    Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/current", method = GET)
    Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/register", method = POST)
    void registerUser(@Valid @RequestBody User user) {
        userService.createUser(user);
    }
}
