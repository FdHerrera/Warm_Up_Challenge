package com.alkemy.warmup.auth.controller;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.model.RegistrationRequest;
import com.alkemy.warmup.auth.service.AppUserService;
import com.alkemy.warmup.auth.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final RegistrationService service;

    @PostMapping(path = "/signup")
    public ResponseEntity<AppUser> signUpUser(@RequestBody RegistrationRequest request){
        AppUser newUser = service.register(request);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }
}
