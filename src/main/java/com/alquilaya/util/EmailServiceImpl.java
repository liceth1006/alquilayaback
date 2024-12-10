package com.alquilaya.util;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.alquilaya.dto.UserRegistrationDTO;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Constructor con inyección de dependencias
    public EmailServiceImpl(JavaMailSender javaMailSender, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.javaMailSender = javaMailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    // Método para enviar el email de restablecimiento de contraseña
    public void sendPasswordResetEmail(UserRegistrationDTO user, String resetToken,String link) throws MessagingException {
        String resetLink = link + resetToken;

        // Crear el mensaje MIME
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Detalles del correo
        helper.setTo(user.getEmail());
        helper.setSubject("Password Reset - Alquilaya");
        helper.setFrom(fromEmail);  // Usar el correo desde el que se enviarán los mensajes
        helper.setText("Hello " + user.getFirstName() + ",\n\nClick on the link to reset your password: " + resetLink, true);

        // Enviar el correo
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmail(String sendTo, String sendFrom, String name, String subject,
                          String template, String replyTo, String link) throws Exception {
        try {
            // Configurar el mensaje MIME
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(sendTo);
            helper.setFrom(sendFrom);
            helper.setSubject(subject);
            helper.setReplyTo(replyTo);

            // Llenar la plantilla con los datos
            Map<String, Object> model = new HashMap<>();
            model.put("name", name);
            model.put("link", link);

            String body = processTemplate(model, template);
            helper.setText(body, true); // true para usar HTML

            // Enviar el correo
            javaMailSender.send(message);
            System.out.println("Correo enviado a " + sendTo);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new Exception("Error al enviar el correo electrónico", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error general al enviar el correo electrónico", e);
        }
    }

    /**
     * Procesa la plantilla con los datos proporcionados.
     * 
     * @param model Los datos para rellenar la plantilla.
     * @param template La plantilla a procesar.
     * @return El cuerpo del correo generado.
     * @throws Exception Si ocurre un error al procesar la plantilla.
     */
    private String processTemplate(Map<String, Object> model, String template) throws Exception {
        try {
            // Configurar el FreeMarker y cargar la plantilla
            StringWriter writer = new StringWriter();
            freeMarkerConfigurer.getConfiguration().getTemplate(template).process(model, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new Exception("Error al procesar la plantilla", e);
        }
    }
}
