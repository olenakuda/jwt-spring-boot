package com.example.token.controller;

import com.example.token.service.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginEndpoint {

  private final TokenGenerator jwtGenerator;

  @GetMapping
  String jwt(Authentication authentication) {
    return jwtGenerator.generate(authentication);
  }
}
