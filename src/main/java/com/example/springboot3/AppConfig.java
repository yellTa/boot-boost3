package com.example.springboot3;

import com.example.springboot3.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    private final LoginSuccessHandler loginSuccessHandler;

    public AppConfig(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/reservationInfos")
                .authenticated()
                .anyRequest()
                .permitAll()
            )
            .formLogin((form) -> form.loginPage("/login")
                .successHandler(loginSuccessHandler)
                .permitAll())
            .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("testuser")
            .password("password")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

}
