package com.Dual2024.ProjectCompetition.Config.SecurityConfig.Filter;

import com.Dual2024.ProjectCompetition.Business.Service.Security.JwtService;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.DataAccess.Model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter class for JWT authentication.
 *
 * <p>This filter intercepts incoming requests and checks for the presence of a valid JWT (JSON Web Token) in the "Authorization" header.
 * If a valid JWT is found, it extracts the user details and sets up Spring Security's authentication context accordingly.</p>
 *
 * <p>This class extends {@link OncePerRequestFilter}, ensuring that it is only executed once per request.</p>
 *
 * @author Francisco Balonero Olivera
 * @see org.springframework.web.filter.OncePerRequestFilter
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * The User DAO.
     */
    @Autowired
    UserDAO userDAO;

    /**
     * The JWT service.
     */
    @Autowired
    JwtService jwtService;

    /**
     * Handles the processing of each incoming HTTP request to check for JWT authentication.
     *
     * @param request     The HTTP request
     * @param response    The HTTP response
     * @param filterChain The filter chain for handling the request
     * @throws ServletException If an exception occurs during servlet processing
     * @throws IOException      If an I/O exception occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.split(" ")[1];
        String email = jwtService.extractEmail(jwt);
        User user = null;
        try {
            user = userDAO.findByEmail(email);
        } catch (DataException ignored) {
        }
        assert user != null;
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
