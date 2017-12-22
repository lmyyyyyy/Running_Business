package com.running.business.exception;

import com.running.business.common.ResultEnum;

/**
 * 业务异常
 *
 */
public class AppException extends RuntimeException{

	private static final long serialVersionUID = -5416764873386799668L;

	private String errorCode;

	public AppException() {

	}
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

	public AppException(ResultEnum resultEnum) {
		super(resultEnum.getCode());
		this.errorCode = resultEnum.getMsg();
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

