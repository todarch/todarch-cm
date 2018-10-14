package com.todarch.cm.infrastructure.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

  /**
   * JsonDeserializer expects to find the event class on this side,
   * and fails because todarch.um.* event definitions will not be here.
   * Change the consumer deserializer to String, and define this bean.
   * https://stackoverflow.com/questions/50478267/classnotfoundexception-with-kafka-consumer/50489352#50489352
   */
  @Bean
  public StringJsonMessageConverter jsonConverter() {
    return new StringJsonMessageConverter();
  }

}
