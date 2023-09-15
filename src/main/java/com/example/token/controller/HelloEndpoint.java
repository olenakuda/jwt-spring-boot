package com.example.token.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndpoint {

  @GetMapping("authorized/customized")
  public String customized(Authentication authentication) {
    return "Hello, " + authentication.getName() + "!";
  }

  @GetMapping("unauthorized")
  String unauthorized() {
    return "This will never be displayed due to lack of authorization.";
  }

  @GetMapping("authorized")
  String authorized() {
    return "You are authorized.";
  }
}
