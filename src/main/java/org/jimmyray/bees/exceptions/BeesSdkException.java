package org.jimmyray.bees.exceptions;

public class BeesSdkException extends Exception {

	private static final long serialVersionUID = -3411374760044101019L;

	// Parameterless Constructor
	public BeesSdkException() {
	}

	// Constructor that accepts a message
	public BeesSdkException(String message) {
		super(message);
	}

}
