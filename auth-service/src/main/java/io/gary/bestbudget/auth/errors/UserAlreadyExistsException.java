package io.gary.bestbudget.auth.errors;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super(String.format("User '%s' already exists", username));
    }
}
