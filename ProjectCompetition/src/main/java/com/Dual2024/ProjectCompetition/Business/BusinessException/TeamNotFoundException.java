package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class TeamNotFoundException extends BusinessException {

	private static final long serialVersionUID = 7768296815177248971L;

	public TeamNotFoundException(String message) {
		super(message);
	}

	public TeamNotFoundException(String message, Exception e) {
		super(message, e);
	}
}
