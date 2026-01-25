//////package project.spring_rest.config;
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.authentication.AuthenticationProvider;
//////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//////import org.springframework.security.config.Customizer;
//////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.http.SessionCreationPolicy;
//////import org.springframework.security.core.userdetails.UserDetailsService;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////import project.spring_rest.filters.JwtFilter;
//////
//////
//////@Configuration
//////@EnableWebSecurity
//////public class SecurityConfiguration {
//////
//////    @Autowired
//////    private UserDetailsService userDetailsService;
//////
//////    @Autowired
//////    private JwtFilter jwtFilter;
//////
//////
//////    @Bean
//////    public AuthenticationProvider authProvider() {
//////        DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userDetailsService);
//////        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//////
//////        return provider;
//////    }
//////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http){
//////
//////        http.csrf(customizer -> customizer.disable())
//////                .authorizeHttpRequests(request -> request
//////                        .requestMatchers("/register","/login")
//////                        .permitAll()
//////                        .anyRequest().authenticated())
//////                .httpBasic(Customizer.withDefaults())
//////
//////
//////                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//////        return http.build();
//////    }
//////    @Bean
//////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
//////
//////        return config.getAuthenticationManager();
//////    }
//////}
////
//////package project.spring_rest.config;
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.http.HttpMethod;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.authentication.AuthenticationProvider;
//////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//////import org.springframework.security.config.Customizer;
//////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.http.SessionCreationPolicy;
//////import org.springframework.security.core.userdetails.UserDetailsService;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////import project.spring_rest.filters.JwtFilter;
//////
//////@Configuration
//////@EnableWebSecurity
//////public class SecurityConfiguration {
//////
//////    @Autowired
//////    private UserDetailsService userDetailsService;
//////
//////    @Autowired
//////    private JwtFilter jwtFilter;
//////
//////    @Bean
//////    public AuthenticationProvider authProvider() {
//////        DaoAuthenticationProvider provider =
//////                new DaoAuthenticationProvider(userDetailsService);
//////        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//////        return provider;
//////    }
//////
//////    @Bean
//////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//////
//////        http
//////                .cors(Customizer.withDefaults())       // 🔥 enable CORS
//////                .csrf(csrf -> csrf.disable())
//////                .authorizeHttpRequests(auth -> auth
//////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 🔥 allow preflight
//////                        .requestMatchers("/register", "/login", "/oauth2/**").permitAll()
//////                        .anyRequest().authenticated()
//////                )
//////                .sessionManagement(session ->
//////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//////                )
//////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//////
//////        return http.build();
//////    }
//////
//////    @Bean
//////    public AuthenticationManager authenticationManager(
//////            AuthenticationConfiguration config) throws Exception {
//////        return config.getAuthenticationManager();
//////    }
//////}
////
////package project.spring_rest.config;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.CorsConfigurationSource;
////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
////import project.spring_rest.filters.JwtFilter;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfiguration {
////
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    @Bean
////    public AuthenticationProvider authProvider() {
////        DaoAuthenticationProvider provider =
////                new DaoAuthenticationProvider(userDetailsService);
////        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
////        return provider;
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http
////                .csrf(csrf -> csrf.disable())
////                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // preflight
////                        .requestMatchers("/register", "/login", "/oauth2/**","/oauth-success").permitAll()
////                        .anyRequest().authenticated()
////                ) .oauth2Login(oauth2 -> oauth2
////                                .defaultSuccessUrl("/oauth-success", true)
////                        // Redirect to /hello after login
////                )
////                .sessionManagement(session ->
////                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////
////    // 🔥 bind cors here
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("http://localhost:5173");
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("*");
////
////        UrlBasedCorsConfigurationSource source =
////                new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", config);
////        return source;
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(
////            AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////}
////
//
//package project.spring_rest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import project.spring_rest.filters.JwtFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        return provider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                        .requestMatchers(
//                                "/register",
//                                "/login",
//                                "/oauth2/**",
//                                "/oauth-success"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth -> oauth
//                        .defaultSuccessUrl("/oauth-success", true)
//                )
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//
//                // 🔥 IMPORTANT: JWT runs AFTER OAuth
//                .addFilterAfter(jwtFilter, OAuth2LoginAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:5173");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
//

package project.spring_rest.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;
import project.spring_rest.filters.JwtFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/login", "/register", "/oauth2/**", "/oauth-success").permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .oauth2Login(oauth ->
                        oauth.defaultSuccessUrl("/oauth-success", true)
                )

                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint((req, res, e) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration c = new CorsConfiguration();
        c.setAllowCredentials(true);
        c.setAllowedOriginPatterns(List.of(
                "http://localhost:5173",
                "https://job-portal-frontend-by-pl.netlify.app",
                "https://*.netlify.app"
        ));
        c.addAllowedHeader("*");
        c.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource src =
                new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", c);
        return src;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
