package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class DuplicatedNameException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -2232920202794886547L;

    public DuplicatedNameException(String message) {
        super(message);
    }

    public DuplicatedNameException(String message, Exception e) {
        super(message, e);
    }
}
