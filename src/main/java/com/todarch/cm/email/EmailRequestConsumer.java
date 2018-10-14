package com.todarch.cm.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Reacts to events published to Kafka email topic.
 *
 * @author selimssevgi
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmailRequestConsumer {

  private static final String EMAIL_TOPIC = "dercg7n2-email";

  private final EmailService emailService;

  /**
   * Sends the activation email to registered user email.
   */
  @KafkaListener(topics = EMAIL_TOPIC, groupId = "cm-id")
  public void handleNewUserRegistration(NewUserRegistered newUserRegistered) {
    log.info("Receiver on {}: {}", EMAIL_TOPIC, newUserRegistered.toString());
    emailService.sendSimpleMessage(
        newUserRegistered.getEmail(),
        "Todarch Account Activation",
        newUserRegistered.getActivationUrl()
    );
  }
}
