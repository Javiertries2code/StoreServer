package com.elorrieta.storeapi.service.email;

import java.util.Properties;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value; // ✅ Esta es la buena








@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {

	
    @Value("${email.user}")
    private String user;

    @Value("${email.pass}")
    private String pass;

    @Value("${email.subject:Low on stocks}")
    private String subject;

    @Value("${email.host:smtp.gmail.com}")
    private String smtp_host;

    @Value("${email.port:465}")
    private int smtp_port;
    
    @PostConstruct
    public void init() {
        log.info(" EmailService cargado correctamente");
    }

    
	/**
	 * Sends the given <b>text</b> from the <b>sender</b> to the <b>receiver</b>. In
	 * any case, both the <b>sender</b> and <b>receiver</b> must exist and be valid
	 * mail addresses. The sender, mail's FROM part, is taken from this.user by
	 * default<br/>
	 * <br/>
	 *
	 * Note the <b>user</b> and <b>pass</b> for the authentication is provided in
	 * the class constructor. Ideally, the <b>sender</b> and the <b>user</b>
	 * coincide.
	 *
	 * @param receiver The mail's TO part
	 * @param subject  The mail's SUBJECT
	 * @param text     The proper MESSAGE
	 * @throws MessagingException Is something awry happens
	 *
	 */
	public void sendMail(String receiver, String text) throws MessagingException {

		  log.debug("Preparing to send email to: {}", receiver);
	        log.debug("Message content:\n{}", text);		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true"); // Activa STARTTLS
		properties.put("mail.smtp.host", smtp_host);
		properties.put("mail.smtp.port", smtp_port);
		properties.put("mail.smtp.ssl.enable", "false"); // Desactiva SSL puro

		
		// Mail properties
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", true);
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", smtp_host);
//		properties.put("mail.smtp.port", smtp_port);
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.trust", smtp_host);
//		properties.put("mail.imap.partialfetch", false);
		// Authenticator knows how to obtain authentication for a network connection.
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		// MIME message to be sent
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver)); // Ej: receptor@gmail.com
		message.setSubject(subject); // Asunto del mensaje
		// A mail can have several parts
		Multipart multipart = new MimeMultipart();
		// A message part (the message, but can be also a File, etc...)
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(text, "text/html");
		multipart.addBodyPart(mimeBodyPart);
		// Adding up the parts to the MIME message
		message.setContent(multipart);
		// And here it goes...
		Transport.send(message);
	}
/*
	public static void main(String[] args) {
		String user = "sender@gmail.com";
		String pass = "The Generated Pass";
		String to = "receiver@gmail.com";
		String subject = "Mensaje de prueba";
		String message = "Correo de vital importancia";
		EmailService emailService = new EmailService(user, pass, "smtp.gmail.com", 465);
		try {
			emailService.sendMail(to, subject, message);
			System.out.println("Ok, mail sent!");
		} catch (MessagingException e) {
			System.out.println("Doh! " + e.getMessage());
		}
	}*/
}


