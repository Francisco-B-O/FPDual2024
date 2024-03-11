package com.Dual2024.ProjectCompetition.Business.Service.Security;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationRequest;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationResponse;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the AuthenticationService interface.
 *
 * @author Francisco Balonero Olivera
 * @see AuthenticationService
 */
@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * The Authentication manager.
     */
    @Autowired
    AuthenticationManager authenticationManager;
    /**
     * The User dao.
     */
    @Autowired
    UserDAO userDAO;
    /**
     * The Model to bo converter.
     */
    @Autowired
    ModelToBOConverter modelToBOConverter;
    /**
     * The Jwt service.
     */
    @Autowired
    JwtService jwtService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        log.info("Attempting to log in user with email: {}", request.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        try {
            UserBO user = modelToBOConverter.userModelToBO(userDAO.findByEmail(authenticationToken.getName()));
            String jwt = jwtService.generateToken(user, generateExtraClaims(user));
            log.info("User {} successfully logged in.", user.getNick());
            return new AuthenticationResponse(jwt);
        } catch (DataException e) {
            log.error("User not found", e);
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public Long getUserAuthenticated() throws BusinessException {
        Long id = null;
        Object aux = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (aux instanceof UserDetails) {
            String email = ((UserDetails) aux).getUsername();
            try {
                UserBO authenticatedUser = modelToBOConverter.userModelToBO(userDAO.findByEmail(email));
                id = authenticatedUser.getId();
                log.info("Retrieved authenticated user with ID: {}", id);
            } catch (DataException e) {
                log.error("Error retrieving authenticated user", e);
                throw new BusinessException(e.getMessage(), e);
            }
        }
        return id;
    }

    private Map<String, Object> generateExtraClaims(UserBO user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("nick", user.getNick());
        extraClaims.put("roles", user.getRoles());
        return extraClaims;
    }
}
