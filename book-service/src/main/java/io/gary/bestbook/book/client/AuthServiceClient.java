package io.gary.bestbook.book.client;

import io.gary.bestbook.book.model.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping("/uaa/users")
    Collection<UserDto> listAllUsers();

}
