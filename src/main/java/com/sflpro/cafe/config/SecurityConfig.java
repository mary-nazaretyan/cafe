package com.sflpro.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()

            .antMatchers("/sign-in").permitAll()
            .antMatchers("/add-product", "/add-table", "/add-user").hasAuthority("MANAGER")
            .antMatchers("/add-order", "/edit-order").hasAuthority("WAITER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/sign-in")
            .defaultSuccessUrl("/home")
            .failureUrl("/error")
            .usernameParameter("username").passwordParameter("password")
            .and()
            .logout().logoutSuccessUrl("/sign-out");



    }

}
