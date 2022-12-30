package com.example.demo.security;

import com.example.demo.filter.CustomAuthenticationFilter;
import com.example.demo.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService (  userDetailsService).passwordEncoder ( bCryptPasswordEncoder );
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean ());

        customAuthenticationFilter.setFilterProcessesUrl ( "/api/login" );

        http.csrf ().disable ();
        http.sessionManagement ().sessionCreationPolicy ( STATELESS );
        http.authorizeRequests ().antMatchers (  "/").permitAll ()
                                .antMatchers(POST, "/api/v1/orders").hasAnyAuthority("ROLE_CLIENT")
                                .antMatchers(GET, "/api/v1/users/user").hasAnyAuthority("ROLE_CLIENT")
                                .antMatchers ("/api/v1/users/**").hasAnyAuthority ( "ROLE_ADMIN" )
                                .antMatchers(POST, "/api/v1/products/addProduct").hasAnyAuthority("ROLE_ADMIN")
                                .antMatchers (DELETE,"/api/v1/products/product").hasAnyAuthority ( "ROLE_ADMIN" )
                                .antMatchers ( "/api/v1/products/**").permitAll ()
        //http.authorizeRequests ().antMatchers (POST, "/api/v1/users/").hasAnyAuthority ( "ROLE_ADMIN" );
                                .anyRequest ().authenticated ();
        http.addFilter ( customAuthenticationFilter  );
        http.addFilterBefore ( new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean ();
    }
}
