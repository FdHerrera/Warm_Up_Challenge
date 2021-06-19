package com.alkemy.warmup.auth.service;

import com.alkemy.warmup.auth.model.ConfirmationToken;
import com.alkemy.warmup.repos.ConfirmationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo tokenRepo;


    public void saveToken(ConfirmationToken usersToken) {
        tokenRepo.save(usersToken);
    }

    public ConfirmationToken getTokenByToken(String token) {
        return tokenRepo.findByToken(token).orElseThrow(()->new IllegalStateException("No se encuentra éste token"));
    }
}
