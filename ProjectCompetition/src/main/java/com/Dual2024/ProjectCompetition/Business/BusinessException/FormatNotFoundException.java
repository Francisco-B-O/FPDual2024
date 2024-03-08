package com.Dual2024.ProjectCompetition.Business.BusinessException;

import java.io.Serial;

public class FormatNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 2681247367923842688L;

    public FormatNotFoundException(String message, Exception e) {
        super(message, e);

    }

    public FormatNotFoundException(String message) {
        super(message);

    }
}
