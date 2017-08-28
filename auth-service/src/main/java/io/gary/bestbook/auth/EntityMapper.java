package io.gary.bestbook.auth;

import io.gary.bestbook.auth.domain.User;
import io.gary.bestbook.auth.domain.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    UserDto toDto(User user);

    User fromDto(UserDto userDto);
}
