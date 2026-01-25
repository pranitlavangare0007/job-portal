package project.spring_rest.services;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.spring_rest.models.Users;
import project.spring_rest.repositry.UserRepo;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public Users saveUser(@NonNull Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Users getUser(Users user) {
        return userRepo.findByUsername(user.getUsername());
    }
}
