package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class UserNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -5004348401632046351L;

    public UserNotFoundException(String message, Exception e) {
        super(message, e);

    }

    public UserNotFoundException(String message) {
        super(message);

    }
}
