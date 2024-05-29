package com.iessotero.divertida.services;

import java.util.Properties;

import org.springframework.stereotype.Service;


//import jakarta.mail.*;
//import jakarta.mail.internet.*;


@Service
public class EmailService {

//
//    public void sendConfirmationEmail(String recipientEmail, String confirmationLink) {
//        // Configuración de las propiedades del servidor SMTP
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", "smtp.example.com");
//        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        // Credenciales del remitente
//        String username = "susanavelascopedraza@gmail.com";
//        String password = "658092588";
//
//        // Crear sesión de correo
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            // Crear mensaje de correo electrónico
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//            message.setSubject("Confirmación de Registro");
//            message.setText("¡Gracias por registrarte en D de Divertida!\nPor favor, haz clic en el siguiente enlace para confirmar tu cuenta:\n" + confirmationLink);
//
//            // Enviar mensaje
//            Transport.send(message);
//
//            System.out.println("¡Correo electrónico de confirmación enviado exitosamente!");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            System.out.println("Error al enviar el correo electrónico de confirmación: " + e.getMessage());
//        }
//    }
}
