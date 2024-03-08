package com.Dual2024.ProjectCompetition.Business.Service.Security;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationRequest;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationResponse;

/**
 * Interface that contains the methods of the authentication service.
 *
 * @author Francisco Balonero Olivera
 */
public interface AuthenticationService {
    /**
     * login method to authenticate a response.
     *
     * @param request the request
     * @return the authentication response
     */
    public AuthenticationResponse login(AuthenticationRequest request);

    /**
     * Method that returns the id of the user who is authenticated.
     *
     * @return the user authenticated
     * @throws BusinessException the business exception
     */
    public Long getUserAuthenticated() throws BusinessException;
}
