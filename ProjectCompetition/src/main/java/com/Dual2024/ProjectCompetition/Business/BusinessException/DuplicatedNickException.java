package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class DuplicatedNickException extends BusinessException {

	private static final long serialVersionUID = 5235794232694134448L;

	public DuplicatedNickException(String message) {
		super(message);
	}

	public DuplicatedNickException(String message, Exception e) {
		super(message, e);
	}
}
