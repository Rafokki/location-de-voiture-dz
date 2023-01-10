package dz.locationvoiture.exceptions;

import lombok.Getter;

@Getter
public class TraitementDemandeReservationException extends BusinessException {

	private final ErrorCode errorCode;

	public enum ErrorCode {
		VOITURE_NON_DISPONIBLE,
		DEMANDE_RESERVATION_NON_DISPONIBLE,
		DEMANDE_RESERVATION_DEJA_TRAITEE,
	}

	public TraitementDemandeReservationException(ErrorCode errorCode, String message, Exception exception) {
		super("TraitementFichierException code=" + errorCode.name() +". " + message, exception);
		this.errorCode = errorCode;
	}
	public TraitementDemandeReservationException(ErrorCode errorCode, String message) {
		super("TraitementFichierException code=" + errorCode.name() +". " + message);
		this.errorCode = errorCode;
	}
}
