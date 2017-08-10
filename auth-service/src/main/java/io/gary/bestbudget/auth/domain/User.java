package io.gary.bestbudget.auth.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

@Data
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    private String password;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return NO_AUTHORITIES;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
