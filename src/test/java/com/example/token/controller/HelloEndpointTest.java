package com.example.token.controller;

import com.example.token.configuration.security.SecurityConfiguration;
import com.example.token.service.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HelloEndpoint.class, TokenGenerator.class, LoginEndpoint.class})
@Import(SecurityConfiguration.class)
class HelloEndpointTest {

  @Autowired
  MockMvc mvc;

  @Test
  void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
    MvcResult result = mvc.perform(get("/login")
            .with(httpBasic("user", "password")))
        .andExpect(status().isOk())
        .andReturn();

    String token = result.getResponse().getContentAsString();

    mvc.perform(get("/authorized/customized")
            .header("Authorization", "Bearer " + token))
        .andExpect(content().string("Hello, user!"));
  }

  @Test
  void rootWhenAuthenticated() throws Exception {
    MvcResult result = mvc.perform(get("/login")
            .with(httpBasic("user", "password")))
        .andExpect(status().isOk())
        .andReturn();

    String token = result.getResponse().getContentAsString();

    mvc.perform(get("/authorized")
            .header("Authorization", "Bearer " + token))
        .andExpect(content().string("You are authorized."));
  }

  @Test
  void rootWhenUnauthenticatedThen401() throws Exception {
    mvc.perform(get("/authorized/customized")).andExpect(status().isUnauthorized());
    mvc.perform(get("/authorized/customized")
            .with(user("user").password("jj")))
        .andExpect(status().isOk());
    mvc.perform(get("/authorized/customized")
            .with(httpBasic("user", "")))
        .andExpect(status().isUnauthorized());
    mvc.perform(get("/authorized/customized")
            .with(httpBasic("user", "dd")))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void tokenWhenUnauthorized() throws Exception {
    mvc.perform(get("/unauthorized"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void tokenWhenBadCredentialsThen401() throws Exception {
    mvc.perform(get("/login"))
        .andExpect(status().isUnauthorized());
  }
}
