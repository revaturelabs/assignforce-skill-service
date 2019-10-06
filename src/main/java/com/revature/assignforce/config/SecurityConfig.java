package com.revature.assignforce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * 
 * SecurityConfig check all users who try to authorize 
 *
 */
@Configuration
// class SecurityConfig extends WebSecurityConfigurerAdapter for checking authorized users
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();
    }

}
