package com.company.enroller.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests()
               .anyRequest().permitAll()
               // .anyRequest().permitAll() - zachowuje wszystko po staremu, permit all itp - trza bylo dodac po zmianie dependency bo nie puszczalo nikogo
               .and()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   }
}