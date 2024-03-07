package com.Dual2024.ProjectCompetition.Business.Service.Security;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationRequest;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.AuthenticationResponse;

public interface AuthenticationService {
	public AuthenticationResponse login(AuthenticationRequest request);

	public Long getUserAuthenticated() throws BusinessException;
}
