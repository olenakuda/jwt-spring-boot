package com.example.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TokenApplication {

  public static void main(String[] args) {
    SpringApplication.run(TokenApplication.class, args);
  }

  //tutorial :
  // https://github.com/spring-projects/spring-security-samples/blob/main/servlet/spring-boot/java/oauth2/authorization-server/src/main/java/example/OAuth2AuthorizationServerSecurityConfiguration.java
}
