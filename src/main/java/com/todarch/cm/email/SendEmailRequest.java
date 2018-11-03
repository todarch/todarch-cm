package com.todarch.cm.email;

import lombok.Data;

import java.util.Map;

@Data
class SendEmailRequest {
  private String to;
  private EmailType emailType;
  private Map<String, String> parameters;
}
