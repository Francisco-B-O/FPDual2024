package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class ModalityNotFoundException extends BusinessException {

	private static final long serialVersionUID = -115495127483418668L;

	public ModalityNotFoundException(String message, Exception e) {
		super(message, e);

	}

	public ModalityNotFoundException(String message) {
		super(message);

	}
}
