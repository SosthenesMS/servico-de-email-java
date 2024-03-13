package com.servico.email.java.Services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {
	
	public void sendEmail(String destinatario, String assuntoEmail, String mensagemEmail) throws MessagingException {
		// Configurações do servidor de e-mail
		String host = "smtp.gmail.com";
		String username = "Username";
		String password = "Password,";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Cria uma sessão com autenticação
		Authenticator authenticator = new Authenticator() {
		    @Override
		    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		    }
		};

		Session session = Session.getInstance(props, authenticator);

		// Cria uma mensagem de e-mail
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
		message.setSubject(assuntoEmail);
		message.setText(mensagemEmail);

		// Envia a mensagem
		Transport.send(message);
		System.out.println("|[INFO] E-mail enviado com sucesso para: " + destinatario);
	}
	

}
