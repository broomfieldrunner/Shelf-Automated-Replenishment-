package com.smartbuy.ocb.exception;

public class OCBException extends Exception {

	public OCBException() {
		System.out.println("Project Specific Logic");
	}

	public OCBException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OCBException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public OCBException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OCBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
