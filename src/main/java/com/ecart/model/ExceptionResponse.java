package com.ecart.model;

public class ExceptionResponse {
	private String status;
	private String message;
	private String statusCode;
	
	public ExceptionResponse(String status,String message,String statusCode) {
		this.status=status;
		this.message=message;
		this.statusCode=statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
