package com.alkemy.warmup.auth.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.registration.token.ConfirmationToken;
import com.alkemy.warmup.auth.registration.token.ConfirmationTokenService;
import com.alkemy.warmup.repos.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepo repo;
    @Autowired
    private final ConfirmationTokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email).
                orElseThrow(
                        ()-> new UsernameNotFoundException("No se encuentra un usuario registrado con este email")
                );
    }

    public String signUpUser(AppUser user){
        boolean userExists = repo.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("Éste email ya está registrado");
        }
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        repo.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user
        );
        tokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void EnableAppUser(String email) {
        repo.enableAppUser(email);
    }
}
