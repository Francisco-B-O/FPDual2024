package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class ActiveTournamentException extends BusinessException {

	private static final long serialVersionUID = -448578762742195642L;

	public ActiveTournamentException(String message) {
		super(message);
	}

	public ActiveTournamentException(String message, Exception e) {
		super(message, e);
	}
}
