package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class FullTeamException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2338863615941165003L;

    public FullTeamException(String message) {
        super(message);
    }

    public FullTeamException(String message, Exception e) {
        super(message, e);
    }
}
