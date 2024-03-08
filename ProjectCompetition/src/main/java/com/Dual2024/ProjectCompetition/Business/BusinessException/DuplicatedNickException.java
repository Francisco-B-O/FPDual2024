package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class DuplicatedNickException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 5235794232694134448L;

    public DuplicatedNickException(String message) {
        super(message);
    }

    public DuplicatedNickException(String message, Exception e) {
        super(message, e);
    }
}
