

package project.spring_rest.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.spring_rest.services.JwtService;

import java.io.IOException;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/oauth-success")
    public void oauthSuccess(HttpServletResponse response) throws IOException {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            response.sendRedirect("http://localhost:5173/login");
            return;
        }

        String email = auth.getName();

        // 🔥 Google users are normal users
        String token = jwtService.generateToken(email, "USER");

        response.sendRedirect(
                "http://localhost:5173/oauth-callback?token=" + token
        );
    }


}
