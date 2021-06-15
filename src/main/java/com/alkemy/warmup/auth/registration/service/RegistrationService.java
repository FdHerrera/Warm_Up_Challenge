package com.alkemy.warmup.auth.registration.service;

import com.alkemy.warmup.auth.registration.request.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "funcionando";
    }
}
