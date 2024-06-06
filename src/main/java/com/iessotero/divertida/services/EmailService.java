package com.iessotero.divertida.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Servicio para el envío de correos electrónicos. Utiliza el protocolo SMTP
 * para enviar correos electrónicos de confirmación.
 */
@Service
public class EmailService {

	/** SMTP host */
	@Value("${mail.smtp.host}")
	private String host;

	/** SMTP port */
	@Value("${mail.smtp.port}")
	private int port;

	/** SMTP SSL */
	@Value("${mail.smtp.ssl.enable}")
	private boolean sslEnable;

	/** SMTP autentication */
	@Value("${mail.smtp.auth}")
	private boolean auth;

	/** SMTP user */
	@Value("${mail.smtp.user}")
	private String username;

	/** SMTP password */
	@Value("${mail.smtp.password}")
	private String password;

	/**
	 * Crear sesion para enviar el email para confirmación
	 * 
	 * @return Session
	 */
	private Session createSession() {

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.ssl.enable", sslEnable);
		props.put("mail.smtp.auth", auth);

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		return Session.getInstance(props, authenticator);
	}

	/**
	 * Enviar email de confirmación
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void sendEmail(String to, String subject, String text) throws MessagingException {

		try {
			Session session = createSession();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);
		} catch (MessagingException e) {

			throw new MessagingException(e.getMessage());
		}
	}
}
