package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class DuplicatedCaptainException extends BusinessException {

	private static final long serialVersionUID = 6009648072538957865L;

	public DuplicatedCaptainException(String message) {
		super(message);
	}

	public DuplicatedCaptainException(String message, Exception e) {
		super(message, e);
	}
}
