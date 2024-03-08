package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class DuplicatedEmailException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 151017868746738093L;

    public DuplicatedEmailException(String message) {
        super(message);
    }

    public DuplicatedEmailException(String message, Exception e) {
        super(message, e);
    }
}
