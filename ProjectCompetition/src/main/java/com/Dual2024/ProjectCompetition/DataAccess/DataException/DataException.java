package com.Dual2024.ProjectCompetition.DataAccess.DataException;

import java.io.Serial;

public class DataException extends Exception {

    @Serial
    private static final long serialVersionUID = -5547352001279328620L;

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Exception e) {
        super(message, e);
    }
}
