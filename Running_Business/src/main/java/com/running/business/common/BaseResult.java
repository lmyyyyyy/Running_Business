package com.running.business.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 自定义响应结构体
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult<T> implements Serializable{

	private static final long serialVersionUID = 4490411721234093609L;
	
	private String code;
	
	private T data;
	
	private ErrorMsg error;
	
	public BaseResult(String code, ErrorMsg error) {
		this.code = code;
		this.error = error;
	}
	
	public BaseResult(String code, T data) {
		this.code = code;
		this.data = data;
	}
	
	public BaseResult(String code) {
		this.code = code;
	}
	
	public BaseResult() {}
	
	public static BaseResult<Object> success() {
		return new BaseResult<Object>(CodeConstants.CODE_0);
	}
	
	public static BaseResult<Object> success(Object obj) {
		return new BaseResult<Object>(CodeConstants.CODE_0, obj);
	}
	
	public static BaseResult<Object> fail() {
		return new BaseResult<Object>(CodeConstants.CODE_1);
	}
	
	public static BaseResult<Object> fail(ErrorMsg error) {
		return new BaseResult<Object>(CodeConstants.CODE_1, error);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ErrorMsg getError() {
		return error;
	}

	public void setError(ErrorMsg error) {
		this.error = error;
	}
	
	
}
