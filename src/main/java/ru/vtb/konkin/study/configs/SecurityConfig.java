package ru.vtb.konkin.study.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String USER = "USER";
    private final String ADMIN = "ADMIN";

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("myuser").password("{noop}userpass").roles(USER)
                .and()
                .withUser("myadmin").password("{noop}adminpass").roles(ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                    .antMatchers("/premiere/buy").hasRole(USER)
                    .antMatchers("/premiere/update/**", "/premiere/add").hasRole(ADMIN)
                    .antMatchers("/premiere/info/**").permitAll()
                .and().csrf().disable().headers().frameOptions().disable();
    }
}
