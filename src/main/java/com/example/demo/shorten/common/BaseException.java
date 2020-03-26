package com.example.demo.shorten.common;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 4329346957049274445L;

	public BaseException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() { return this; }
}
