package com.ensa.msinvoice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        super.configure(auth);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // disabled csrf
        http.csrf().disable();

        //Disabled security for frame
        http.headers().frameOptions().disable();

        // authorize all requests
        http.authorizeRequests().anyRequest().permitAll();


    }



}

