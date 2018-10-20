package com.apibucket.emailservice.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author vinoth
 *
 */
public class JwtTokenMissingException extends AuthenticationException {

	public JwtTokenMissingException(String msg) {
		super(msg);
	}
}
