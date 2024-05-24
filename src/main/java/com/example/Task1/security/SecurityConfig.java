package com.example.Task1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username = ?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM authorities WHERE username = ?");

        return jdbcUserDetailsManager;
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                // UserController endpoints
                                .requestMatchers(HttpMethod.POST, "/users/register", "/users/update-passwords").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users", "/users/{username}").hasAnyRole("ADMIN", "USER")
                                // StoryController endpoints
                                .requestMatchers(HttpMethod.GET, "/stories", "/stories/{id}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/stories/story", "/stories/{storyId}/pages").hasRole("ADMIN")
                                // ReactionController endpoints
                                .requestMatchers(HttpMethod.GET, "/reactions/{pageId}/likes", "/reactions/{pageId}/dislikes").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/reactions/{pageId}/like", "/reactions/{pageId}/dislike").hasRole("ADMIN")
                                // PageController endpoints
                                .requestMatchers(HttpMethod.GET, "/pages", "/pages/story/{storyId}").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated()
                );

       
        http.httpBasic(withDefaults());


   
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
