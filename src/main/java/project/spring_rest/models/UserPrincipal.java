package project.spring_rest.models;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {
    private final Users users;
    public UserPrincipal(Users users){
        this.users=users;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }
}
