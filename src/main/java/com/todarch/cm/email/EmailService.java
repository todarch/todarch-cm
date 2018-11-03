package com.todarch.cm.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Email service.
 *
 * @author selimssevgi
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmailService {

  private final JavaMailSender mailSender;

  private final MailProperties mailProperties;

  /**
   * Sends simple email.
   */
  public void sendSimpleMessage(SendEmailRequest req) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(mailProperties.getUsername());
    message.setTo(req.getTo());
    message.setSubject("Welcome to Todarch!");
    message.setText(req.getParameters().get("activation_url"));
    mailSender.send(message);
  }
}
