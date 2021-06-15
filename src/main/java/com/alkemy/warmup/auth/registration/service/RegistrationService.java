package com.alkemy.warmup.auth.registration.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.model.AppUserRole;
import com.alkemy.warmup.auth.registration.email.EmailValidator;
import com.alkemy.warmup.auth.registration.request.RegistrationRequest;
import com.alkemy.warmup.auth.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService userService;
    private final EmailValidator validator;

    public String register(RegistrationRequest request) {
        boolean isAValidEmail = validator.test(request.getEmail());
        if (!isAValidEmail){
            throw new IllegalStateException("Éste no es un email valido");
        }
        return userService.signUpUser(new AppUser(request.getEmail(), request.getPassword(), AppUserRole.USER));
    }
}
