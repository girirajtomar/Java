package com.example.security;

import com.example.enums.UserApplicationPermissions;
import com.example.enums.UserApplicationRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.enums.UserApplicationPermissions.*;
import static com.example.enums.UserApplicationRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    public UserSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeRequests()
//                .antMatchers("/api/v1/students/**").hasRole(UserApplicationRoles.STUDENT.name())
//                .antMatchers(HttpMethod.POST,"/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api/v1/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/v1/**").hasAnyRole(ADMIN.name(), TRAINEEADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("raj")
                .password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthority())
                .build();
        UserDetails admin = User.builder()
                .username("giriraj")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthority())
                .build();
        UserDetails trainee = User.builder()
                .username("sunita")
                .password(passwordEncoder.encode("password"))
//                .roles(TRAINEEADMIN.name())
                .authorities(TRAINEEADMIN.getGrantedAuthority())
                .build();
        return new InMemoryUserDetailsManager(user,admin,trainee);
    }
}
