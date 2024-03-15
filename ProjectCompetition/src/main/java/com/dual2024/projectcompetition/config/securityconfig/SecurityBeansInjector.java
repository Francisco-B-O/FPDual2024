package com.dual2024.projectcompetition.config.securityconfig;

import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.dataaccess.dataexception.DataException;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Component class responsible for providing security-related beans.
 *
 * <p>This class defines beans required for authentication and password encoding within the security configuration of the application.
 * It includes the {@link AuthenticationManager}, {@link AuthenticationProvider}, {@link PasswordEncoder}, and {@link UserDetailsService} beans.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // In your security configuration or other components, you can autowire the AuthenticationManager bean
 * \@Autowired
 * private AuthenticationManager authenticationManager;
 *
 * // Similarly, you can autowire other security-related beans as needed
 * }
 * </pre>
 *
 * <p>Instances of this class are typically used as a configuration source for the Spring framework, defining beans and their dependencies.</p>
 *
 * @author Francisco Balonero Olivera
 * @see org.springframework.security.authentication.AuthenticationManager
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @see org.springframework.security.crypto.password.PasswordEncoder
 * @see org.springframework.security.core.userdetails.UserDetailsService
 */
@Component
public class SecurityBeansInjector {
    /**
     * The User DAO.
     */
    @Autowired
    UserDAO userDAO;

    /**
     * Creates an AuthenticationManager bean.
     *
     * @param authenticationConfiguration {@link AuthenticationConfiguration} The authentication configuration
     * @return {@link AuthenticationManager} The AuthenticationManager bean
     * @throws Exception If an exception occurs during bean creation
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates an AuthenticationProvider bean.
     *
     * @return {@link DaoAuthenticationProvider} The AuthenticationProvider bean
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Creates a PasswordEncoder bean.
     *
     * @return {@link BCryptPasswordEncoder} The PasswordEncoder bean
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a UserDetailsService bean.
     *
     * @return {@link UserDetailsService} The UserDetailsService bean
     */
    @Bean
    UserDetailsService userDetailsService() {
        return email -> {
            try {
                return userDAO.findByEmail(email);
            } catch (DataException e) {
                throw new NotFoundException("User not found");
            }
        };
    }
}
