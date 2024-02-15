package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class InvalidSizePasswordException extends BusinessException  {


	private static final long serialVersionUID = 7247693039424650597L;

	public InvalidSizePasswordException(String message) {
		super(message);
	}

	public InvalidSizePasswordException(String message, Exception e) {
		super(message, e);
	}
}
