package com.alkemy.warmup.auth.registration.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    private final String email;
    private final String password;
}
