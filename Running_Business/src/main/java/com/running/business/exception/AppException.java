package com.running.business.exception;

/**
 * 业务异常
 *
 */
public class AppException extends RuntimeException{

	private static final long serialVersionUID = -5416764873386799668L;

	private String errorCode;
	
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AppException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

