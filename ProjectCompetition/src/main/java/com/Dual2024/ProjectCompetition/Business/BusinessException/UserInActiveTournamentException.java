package com.Dual2024.ProjectCompetition.Business.BusinessException;

public class UserInActiveTournamentException extends BusinessException {

	private static final long serialVersionUID = -1777617730921569922L;

	public UserInActiveTournamentException(String message) {
		super(message);
	}

	public UserInActiveTournamentException(String message, Exception e) {
		super(message, e);
	}
}
