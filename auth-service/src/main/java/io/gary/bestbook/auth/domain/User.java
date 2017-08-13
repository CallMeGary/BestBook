package io.gary.bestbook.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String emailAddress;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return NO_AUTHORITIES;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
