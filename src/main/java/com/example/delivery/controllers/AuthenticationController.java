package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.LoginForm;
import com.example.delivery.presenters.LoginPresenter;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthenticationController {

   AuthenticationService authenticationService;

   @PostMapping("/login")
   public OkResponse<LoginPresenter> login(@RequestBody LoginForm form) {
       return OkResponse.of(
               new LoginPresenter(authenticationService.login(form))
       );
   }

   @PostMapping("/logout")
   public OkResponse<String> logout() {
       // TODO: Implement logout
       return OkResponse.of(ConstantMessages.SUCCESS);
   }
}
