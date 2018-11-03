package com.todarch.cm.email;

import lombok.Data;

import java.util.Map;

@Data
public class EmailSendRequest {
  private String to;
  private EmailType emailType;
  private Map<String, String> parameters;
}
