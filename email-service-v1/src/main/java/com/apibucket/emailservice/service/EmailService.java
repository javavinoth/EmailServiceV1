package com.apibucket.emailservice.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


/**
 * @author vinoth
 *
 */
@Service
public class EmailService {

	@PreAuthorize("hasRole('ABC')")
	public void sendMail()
	{
		System.out.println("***** EmailService *******");
	}
}
