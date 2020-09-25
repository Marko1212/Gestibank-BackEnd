package com.gesti.bank.config;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = "com.gesti.bank")
public class MailConfig {

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("gestibank1212@gmail.com");
		mailSender.setPassword("lakosepamti");
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.host", "smtp.gmail.com");
		javaMailProperties.put("mail.smtp.port", 465);
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.socketFactory.port", 465);
		javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javaMailProperties.put("mail.debug", "true");
		
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	
	@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException{
		Properties properties = new Properties();
		properties.setProperty("input.encoding", "UTF-8");
		properties.setProperty("output.encoding", "UTF-8");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		
		VelocityEngine velocityEngine = new VelocityEngine(properties);
		return velocityEngine;
	}
}
