package com.ethicalhackingblog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests((authz) -> {
                    authz
                            .requestMatchers("/login/**", "/blog/index.html").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin((formLogin) -> {
                    formLogin
                            .loginPage("/login")
                            .usernameParameter("username")
                            .permitAll()
                            .defaultSuccessUrl("/login", true); // Redirect to "/login" on successful login
                })
                .logout((logout) -> {
                    logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll();
                })
                .httpBasic(withDefaults());

        return http.build();
    }



}
