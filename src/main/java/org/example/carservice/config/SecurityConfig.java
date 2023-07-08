package org.example.carservice.config;

import org.example.carservice.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_USER = Role.RoleName.USER.name();
    private static final String ROLE_ADMIN = Role.RoleName.ADMIN.name();
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/register/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/products/**",
                        "/orders/**",
                        "/cars/**",
                        "/owners/**",
                        "/masters/**",
                        "/services/**")
                .hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.POST,
                        "/products/**",
                        "/cars/**",
                        "/owners/**",
                        "/masters/**",
                        "/orders/**",
                        "/users/**",
                        "/services/**")
                .hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/products/**")
                .hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/products", true)
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

    }
}
