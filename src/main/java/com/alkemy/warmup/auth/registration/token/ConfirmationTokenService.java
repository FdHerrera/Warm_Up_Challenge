package com.alkemy.warmup.auth.registration.token;

import com.alkemy.warmup.repos.ConfirmationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepo repo;

    public void saveConfirmationToken(ConfirmationToken token){
        repo.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return repo.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        repo.updateConfirmedAt(token, LocalDateTime.now());
    }
}
