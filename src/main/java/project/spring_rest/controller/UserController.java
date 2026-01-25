package project.spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.spring_rest.models.Users;
import project.spring_rest.services.JwtService;
import project.spring_rest.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody Users user){
        if(userService.getUser(user) != null){
            return "USER ALREADY EXISTS";
        }
        user.setRole("USER");
        userService.saveUser(user);
        return "USER SAVED";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users){

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        users.getUsername(), users.getPassword()
                )
        );

        Users dbUser = userService.getUser(users);

        return jwtService.generateToken(
                dbUser.getUsername(),
                dbUser.getRole()
        );
    }
}
