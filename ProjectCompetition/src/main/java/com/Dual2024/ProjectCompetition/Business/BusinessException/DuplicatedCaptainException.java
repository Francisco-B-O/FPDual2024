package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class DuplicatedCaptainException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 6009648072538957865L;

    public DuplicatedCaptainException(String message) {
        super(message);
    }

    public DuplicatedCaptainException(String message, Exception e) {
        super(message, e);
    }
}
