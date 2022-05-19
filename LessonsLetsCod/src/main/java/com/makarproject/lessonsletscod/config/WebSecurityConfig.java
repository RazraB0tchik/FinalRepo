package com.makarproject.lessonsletscod.config;

import com.makarproject.lessonsletscod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                        .authorizeRequests()
                        .antMatchers("/", "/registration", "/logout", "/active", "/activateOK/**", "/writePas").permitAll()
                        .anyRequest().authenticated()
                    .and()
                        .formLogin()
                        .loginPage("/login")
                        .permitAll()
                    .and()
                        .logout()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll();
        }

        @Bean
        public PasswordEncoder getPasswordEncoder(){
            return new BCryptPasswordEncoder(10);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/StyleAuntReg/**", "/fonts/**",
                        "/images/**");
    }
}

