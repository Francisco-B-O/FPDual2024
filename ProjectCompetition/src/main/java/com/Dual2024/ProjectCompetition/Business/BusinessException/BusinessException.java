package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class BusinessException extends Exception{

	private static final long serialVersionUID = -1618850050663333984L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception e) {
		super(message, e);
	}
}
