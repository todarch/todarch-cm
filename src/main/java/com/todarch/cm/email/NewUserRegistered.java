package com.todarch.cm.email;

import lombok.Data;

@Data
public class NewUserRegistered {
  private String email;
  private String activationUrl;
}
