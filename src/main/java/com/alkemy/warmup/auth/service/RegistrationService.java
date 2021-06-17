package com.alkemy.warmup.auth.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.model.AppUserRole;
import com.alkemy.warmup.auth.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService userService;

    public AppUser register(RegistrationRequest request) {
        return userService.signUpUser(new AppUser(
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }
}
