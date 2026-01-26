package project.spring_rest.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.spring_rest.repositry.UserRepo;
@Service
public class UserDetailServicesImp implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailServicesImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        var user = userRepo.findByUsername(username);

        if (user != null) {
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())   // 👈 USER / ADMIN
                    .build();
        }
        return User.withUsername(username)
                .password("{noop}")
                .roles("USER")
                .build();
    }
}


