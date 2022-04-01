package com.ssafy.myapp.config;

import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.auth.JwtAuthenticationFilter;
import com.ssafy.myapp.common.auth.SsafyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SsafyUserDetailService ssafyUserDetailService;
    
    @Autowired
    private UserService userService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.ssafyUserDetailService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService))
                .authorizeRequests()
                .antMatchers("/api/users/password").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/users/{userEmail}").authenticated()
                .antMatchers("/api/users/profile").authenticated()
                .antMatchers("/api/users/profile/photo").authenticated()
                .antMatchers("/api/users/profile/preference").authenticated()
                .antMatchers("/api/users/profile/watchHistory").authenticated()
                .antMatchers("/api/users/profile/reviewList").authenticated()
                .antMatchers("/api/users/show/{id}/preference").authenticated()
                .antMatchers("/api/users/show/{id}").authenticated()
                .antMatchers("/api/users/review/ratingStars").authenticated()
                .antMatchers("/api/users/show/preference/tag").authenticated()
                .antMatchers("/api/show/reviews/{reviewId}").authenticated()
                .antMatchers(HttpMethod.POST,"/api/show/reviews/{id}").authenticated()
                .antMatchers(HttpMethod.POST,"/api/show/expectations/{id}").authenticated()
                .antMatchers("/api/show/expectations/{expectationId}").authenticated()
                .and().cors();
    }
}