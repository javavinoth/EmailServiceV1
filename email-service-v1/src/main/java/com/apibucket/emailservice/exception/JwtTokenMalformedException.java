package com.apibucket.emailservice.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author vinoth
 *
 */
public class JwtTokenMalformedException extends AuthenticationException {

	public JwtTokenMalformedException(String msg) {
		super(msg);
	}
}
