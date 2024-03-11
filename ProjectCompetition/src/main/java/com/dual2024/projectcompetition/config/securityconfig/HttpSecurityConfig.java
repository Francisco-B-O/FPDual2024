package com.dual2024.projectcompetition.config.securityconfig;

import com.dual2024.projectcompetition.config.securityconfig.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for HTTP security settings.
 *
 * <p>This class configures various aspects of HTTP security, including CSRF protection, session management, and authentication providers.
 * It also defines a {@link JwtAuthenticationFilter} to be applied before the {@link UsernamePasswordAuthenticationFilter} in the filter chain.</p>
 * <p>Instances of this class are used as part of the overall Spring Security configuration to customize the security settings of your application.</p>
 *
 * @author Francisco Balonero Olivera
 * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
 * @see org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 * @see org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
 * @see org.springframework.security.config.http.SessionCreationPolicy
 * @see org.springframework.security.web.SecurityFilterChain
 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 * @see com.dual2024.projectcompetition.config.securityconfig.filter.JwtAuthenticationFilter
 * @see org.springframework.security.authentication.AuthenticationProvider
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Creates a SecurityFilterChain bean for configuring HTTP security settings.
     *
     * @param http The HttpSecurity object
     * @return The SecurityFilterChain bean
     * @throws Exception If an exception occurs during security configuration
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(
                        sessionManagerConfig -> sessionManagerConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
