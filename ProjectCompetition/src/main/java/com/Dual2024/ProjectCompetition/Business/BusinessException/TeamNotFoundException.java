package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class TeamNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 7768296815177248971L;

    public TeamNotFoundException(String message) {
        super(message);
    }

    public TeamNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
