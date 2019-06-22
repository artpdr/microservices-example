package io.example.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SWAGGER_WHITE_LIST_RESOURCES = {
            "/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // no session, thus no csrf can be enabled
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //never creates an http session
            .and()
                .addFilterAfter(new OncePerRequestFilter() {
                    private final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

                    @Override
                    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                                    HttpServletResponse httpServletResponse,
                                                    FilterChain filterChain) throws ServletException, IOException {
                        String header = httpServletRequest.getHeader("Authorization");
                        if(header == null || !header.startsWith("Bearer ")) {
                            filterChain.doFilter(httpServletRequest, httpServletResponse);  		// If not valid, go to the next filter.
                            return;
                        }

                        String token = header.replace("Bearer ", "");
                        try {	// exceptions might be thrown in creating the claims if for example the token is expired
                            Jws<Claims> jws = Jwts.parser()
                                    .setSigningKey("1234567890123456789012345678901234567890".getBytes("UTF-8"))
                                    .parseClaimsJws(token);

                            String username = jws.getBody().getSubject();
                            logger.info("Username on jwt: {}", username);
                            if(username != null) {

                                // 5. Create auth object
                                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                        username, null, Collections.<GrantedAuthority>emptyList());

                                // 6. Authenticate the user
                                // Now, user is authenticated
                                SecurityContextHolder.getContext().setAuthentication(auth);
                            }

                        } catch (Exception e) {
                            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
                            e.printStackTrace();
                            SecurityContextHolder.clearContext();
                        }

                        // go to the next filter in the filter chain
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                    }
                }, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users", "/users/*/auth", "/users/*/password").permitAll()
                .antMatchers(HttpMethod.GET, "/users/*/confirmation/*", "/users/*/password/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/*/password").permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_WHITE_LIST_RESOURCES).permitAll()
                .anyRequest().authenticated();

    }
}
