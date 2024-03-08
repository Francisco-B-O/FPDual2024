package com.Dual2024.ProjectCompetition.DataAccess.DataException;

import java.io.Serial;

public class NotFoundException extends DataException {

    @Serial
    private static final long serialVersionUID = 8280183600637864595L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Exception e) {
        super(message, e);
    }
}
