package com.todarch.cm.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Reacts to events published to Kafka email topic.
 *
 * @author selimssevgi
 */
@Service
@Slf4j
public class EmailRequestConsumer {

  private static final String EMAIL_TOPIC = "dercg7n2-email";

  @KafkaListener(topics = EMAIL_TOPIC, groupId = "cm-id")
  public void handleNewUserRegistration(NewUserRegistered newUserRegistered) {
    log.info("Receiver on {}: {}", EMAIL_TOPIC, newUserRegistered.toString());
  }
}
