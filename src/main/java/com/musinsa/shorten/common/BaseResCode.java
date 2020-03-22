package com.musinsa.shorten.common;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.springframework.http.HttpStatus;

public enum BaseResCode {

	OK(0, "Success")
	, BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase())
	, NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase())
	, METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
	, INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Exception Occurred")
	, STS_EXCEPTION(900, "Sts Exception")
	, GENERAL_OTHER_EXCEPTION(901, "Unexpected Exception Occurred")
	;

	private final Integer value;
	private final String reason;

	BaseResCode(Integer value, String reason) {
		this.value = value; this.reason = reason;
	}

	public Integer value() {
		return this.value;
	}

	public String reason() {
		return this.reason;
	}

	private static final Set<BaseResCode> errors = ImmutableSet.of(
			BaseResCode.INTERNAL_SERVER_ERROR, BaseResCode.STS_EXCEPTION);

	public static boolean oneOfServerSideError(BaseResCode code) {
		return errors.contains(code);
	}
}
