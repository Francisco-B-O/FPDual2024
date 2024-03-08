package com.Dual2024.ProjectCompetition.Presentation.Exception;

import java.io.Serial;

public class NotFoundException extends PresentationException {

    @Serial
    private static final long serialVersionUID = -7198817472286130251L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Exception e) {
        super(message, e);
    }
}
