package com.apibucket.emailservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apibucket.emailservice.service.EmailService;


/**
 * @author vinoth
 *
 */
@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public void sendEmail()
	{
		emailService.sendMail();
		System.out.println("****** EMail is Sending ********");
	}
	
	@PostMapping("/retrive")
	public void retriveEmail()
	{
		emailService.sendMail();
		System.out.println("****** retrive Email ********");
	}
}
