package com.gesti.bank.service.impl;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.gesti.bank.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Override
	public void sendVerificationEmail(String name, String username, String password, String emailTo) {
		VelocityContext context = new VelocityContext();
		context.put("name", name);
		context.put("username", username);
		context.put("password", password);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setSubject("GestiBank account created!");
			mimeMessageHelper.setFrom("gestibank1212@gmail.com");
			mimeMessageHelper.setTo(emailTo);
			mimeMessageHelper.setText(getContentFromTemplateVerification(context), true);
			new Thread(() -> {
				mailSender.send(mimeMessageHelper.getMimeMessage());
			}).start();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String getContentFromTemplateVerification(VelocityContext model) {
		StringWriter content = new StringWriter();
		try {
			velocityEngine.mergeTemplate("/templates/verification.vm", "UTF-8", model, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	public String getContentFromTemplateReceipt(VelocityContext model) {
		StringWriter content = new StringWriter();
		try {
			velocityEngine.mergeTemplate("/templates/receipt.vm", "UTF-8", model, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	@Override
	public void sendConfirmationOfReceiptOfRequestEmail(String name, String emailTo) {
		VelocityContext context = new VelocityContext();
		context.put("name", name);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setSubject("Thank you for your request for Bank Account Creation!");
			mimeMessageHelper.setFrom("gestibank1212@gmail.com");
			mimeMessageHelper.setTo(emailTo);
			mimeMessageHelper.setText(getContentFromTemplateReceipt(context), true);
			new Thread(() -> {
				mailSender.send(mimeMessageHelper.getMimeMessage());
			}).start();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendRejectionEmail(String name, String emailTo) {
		VelocityContext context = new VelocityContext();
		context.put("name", name);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setSubject("Your request for Bank Account has been rejected");
			mimeMessageHelper.setFrom("gestibank1212@gmail.com");
			mimeMessageHelper.setTo(emailTo);
			mimeMessageHelper.setText(getContentFromTemplateRejection(context), true);
			new Thread(() -> {
				mailSender.send(mimeMessageHelper.getMimeMessage());
			}).start();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public String getContentFromTemplateRejection(VelocityContext model) {
		StringWriter content = new StringWriter();
		try {
			velocityEngine.mergeTemplate("/templates/rejection.vm", "UTF-8", model, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	@Override
	public void sendChequeBookCreationConfirmationEmail(String name, String bankAccountTypeName, String bankAccountNumber, String emailTo) {
		VelocityContext context = new VelocityContext();
		context.put("name", name);
	 context.put("bankAccountTypeName", bankAccountTypeName);
	 context.put("bankAccountNumber", bankAccountNumber);

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setSubject("Your request for Cheque Book has been accepted!");
			mimeMessageHelper.setFrom("gestibank1212@gmail.com");
			mimeMessageHelper.setTo(emailTo);
			mimeMessageHelper.setText(getContentFromTemplateChequier(context), true);
			new Thread(() ->  {
				mailSender.send(mimeMessageHelper.getMimeMessage());
			}).start();
		}catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	public String getContentFromTemplateChequier(VelocityContext model) {
		StringWriter content = new StringWriter();
		try {
			velocityEngine.mergeTemplate("/templates/chequier.vm", "UTF-8", model, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
