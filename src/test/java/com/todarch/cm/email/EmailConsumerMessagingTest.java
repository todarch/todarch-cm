package com.todarch.cm.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
    repositoryRoot = "https://raw.githubusercontent.com/todarch/todarch-mvn-repo/master",
    ids = "com.todarch:um:+:stubs:8080",
    stubsMode = StubRunnerProperties.StubsMode.REMOTE)
public class EmailConsumerMessagingTest {

  @Autowired
  private StubTrigger stubTrigger; // auto-configured by spring

  @MockBean
  private EmailService emailService;

  private ArgumentCaptor<SendEmailRequest> emailReqArgCaptor =
      ArgumentCaptor.forClass(SendEmailRequest.class);

  @Test
  public void shouldSendActivationEmail() {
    // given:

    // when:
    // simulate external service publishing the message
    stubTrigger.trigger("send_activation_email");

    // then:
    verify(emailService, times(1))
        .sendSimpleMessage(emailReqArgCaptor.capture());

    SendEmailRequest capturedEmailReq = emailReqArgCaptor.getValue();
    assertThat(capturedEmailReq.getTo()).isEqualTo("test@user.com");
    assertThat(capturedEmailReq.getEmailType()).isEqualTo(EmailType.ACTIVATION_EMAIL);

    Map<String, String> parameters = capturedEmailReq.getParameters();
    assertThat(parameters).isNotEmpty();
    assertThat(parameters.get("activation_url")).isNotNull();
  }
}
