package com.servico.email.java;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.servico.email.java.Services.SendEmail;

import jakarta.annotation.PostConstruct;



@SpringBootApplication
public class ServicoDeEmailJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoDeEmailJavaApplication.class, args);
	}
	
	
	@Autowired
	SendEmail sendEmail;
	
	@PostConstruct
	public void init() {
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				
				try {
					String destinatario = "email-destino";
					String assunto = "TESTE ENVIO DE EMAIL - JAVA";
					String mensagem = "Teste de envio de e-mail em java!";
					
					sendEmail.sendEmail(destinatario, assunto, mensagem);
				} catch (Exception e) {
					System.out.println("|[ERROR] Ero ao tentar enviar o e-mail! --> " + e.getStackTrace() + e.getMessage());
				}
				
				
			}
			
		},1, 2 * 60 * 1000);
		
		
		
	}
	

}
