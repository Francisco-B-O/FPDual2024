package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class FullTournamentException extends BusinessException {

	private static final long serialVersionUID = -2375722109173938280L;

	public FullTournamentException(String message) {
		super(message);
	}

	public FullTournamentException(String message, Exception e) {
		super(message, e);
	}
}
