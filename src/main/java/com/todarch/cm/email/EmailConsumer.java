package com.todarch.cm.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * Consumes the email sending requests from the messaging system.
 *
 * @author selimssevgi
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmailConsumer { // EmailListener

  private final EmailService emailService;

  /**
   * Triggers new email sending based on the request coming from message system.
   */
  @StreamListener(target = Sink.INPUT)
  public void processRegistrationEmail(EmailSendRequest emailSendRequest) {
    SendEmailRequest req = new SendEmailRequest();
    req.setTo(emailSendRequest.getTo());
    req.setParameters(emailSendRequest.getParameters());
    req.setEmailType(emailSendRequest.getEmailType());
    log.info("New user registered:" + emailSendRequest);
    emailService.sendSimpleMessage(req);
  }
}
