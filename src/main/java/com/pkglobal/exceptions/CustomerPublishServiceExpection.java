package com.pkglobal.exceptions;

public class CustomerPublishServiceExpection extends RuntimeException {

	private static final long serialVersionUID = 1508326122999959630L;
	private final String statusCode;
	private final String statusMessage;

	public CustomerPublishServiceExpection(String statusCode, String statusMessage) {
		super(statusMessage);
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
}
