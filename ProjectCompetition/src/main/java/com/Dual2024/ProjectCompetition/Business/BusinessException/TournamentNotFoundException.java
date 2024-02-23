package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class TournamentNotFoundException extends BusinessException {

	private static final long serialVersionUID = 271957121025982489L;

	public TournamentNotFoundException(String message, Exception e) {
		super(message, e);

	}

	public TournamentNotFoundException(String message) {
		super(message);

	}
}
