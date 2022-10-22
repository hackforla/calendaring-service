package com.hfla.service.calendar.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hfla.service.calendar.services.AuthService;
import com.nylas.RequestFailedException;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
  
  private AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }
  
  
  @GetMapping(path = "/login")
  public ModelAndView login() {
    return new ModelAndView(authService.hostedAuthUrlRedirect());
  }

  @GetMapping(path = "/token")
  public String getToken(@RequestParam("code") String code) throws IOException, RequestFailedException {
    return authService.getAccessToken(code);
  }

  @GetMapping(path = "/login/native")
  public ModelAndView loginNative() throws IOException {
    return new ModelAndView(authService.nativeAuthRedirect());
  }
}