package dz.locationvoiture.exceptions;

import java.util.Optional;

/**
 * classe parente des exception métiers
 */
public class BusinessException extends Exception {

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception exception) {
		super(message, exception);
	}
}
