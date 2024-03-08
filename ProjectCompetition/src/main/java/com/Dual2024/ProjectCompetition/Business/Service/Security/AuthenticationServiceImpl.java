package com.Dual2024.ProjectCompetition.Business.Service.Security;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.Converters.ModelToBOConverter;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.DataAccess.DAO.UserDAO;
import com.Dual2024.ProjectCompetition.DataAccess.DataException.DataException;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationRequest;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationResponse;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
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
 */
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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        try {
            UserBO user = modelToBOConverter.userModelToBO(userDAO.findByEmail(authenticationToken.getName()));
            String jwt = jwtService.generateToken(user, generateExtraClaims(user));
            return new AuthenticationResponse(jwt);
        } catch (DataException e) {
            throw new NotFoundException("User not found");
        }

    }

    public Long getUserAuthenticated() throws BusinessException {
        Long id = null;
        Object aux = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (aux instanceof UserDetails) {
            String email = ((UserDetails) aux).getUsername();
            try {
                UserBO authenticatedUser = modelToBOConverter.userModelToBO(userDAO.findByEmail(email));
                id = authenticatedUser.getId();
            } catch (DataException e) {
                throw new BusinessException(e.getMessage(), e);
            }
        }
        return id;
    }

    private Map<String, Object> generateExtraClaims(UserBO user) {
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        extraClaims.put("id", user.getId());
        extraClaims.put("nick", user.getNick());
        extraClaims.put("roles", user.getRoles());
        return extraClaims;

    }
}
