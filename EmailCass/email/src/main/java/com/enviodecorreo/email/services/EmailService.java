package com.enviodecorreo.email.services;

import com.enviodecorreo.email.Repository.EmailRepository;
import com.enviodecorreo.email.Entity.Email;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(Email email) {
        try {
            log.info("[MAIL] Enviando a {} | subject='{}'", email.getTo(), email.getSubject());

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), true);

            javaMailSender.send(mimeMessage);
            log.info("[MAIL] Enviado OK. Persistiendo en Mongo...");

            // IMPORTANTE: asegúrate que Email.java tenga @Document(collection = "correos")
            Email saved = emailRepository.save(email);

            log.info("[MONGO] Guardado OK id={} en colección 'correos'", 
                     (saved != null ? saved.getId() : "null"));

        } catch (Exception ex) {
            log.error("[ERROR] Falló envío o persistencia en Mongo", ex);
            throw new RuntimeException("Error enviando/persistiendo email", ex);
        }
    }
}
