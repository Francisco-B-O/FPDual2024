package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class TeamInActiveTournamentException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 4447640950544648399L;

    public TeamInActiveTournamentException(String message) {
        super(message);
    }

    public TeamInActiveTournamentException(String message, Exception e) {
        super(message, e);
    }
}
