package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class DuplicatedNameAndModalityException extends BusinessException {

    @Serial
    private static final long serialVersionUID = -4699899582060420276L;

    public DuplicatedNameAndModalityException(String message) {
        super(message);
    }

    public DuplicatedNameAndModalityException(String message, Exception e) {
        super(message, e);
    }
}
