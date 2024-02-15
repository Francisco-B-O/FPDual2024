package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class DuplicatedEmailException extends BusinessException {

	private static final long serialVersionUID = 151017868746738093L;

	public DuplicatedEmailException(String message) {
		super(message);
	}

	public DuplicatedEmailException(String message, Exception e) {
		super(message, e);
	}
}
