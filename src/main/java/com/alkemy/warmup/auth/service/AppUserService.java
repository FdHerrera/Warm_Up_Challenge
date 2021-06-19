package com.alkemy.warmup.auth.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.model.ConfirmationToken;
import com.alkemy.warmup.auth.model.RegistrationRequest;
import com.alkemy.warmup.repos.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final AppUserRepo repo;
    private final ConfirmationTokenService tokenService;

    public String signUpUser(AppUser user){
        boolean userExists = repo.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("Éste email ya está registrado");
        }
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        repo.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken usersToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.saveToken(usersToken);
        return token;
    }

    public AppUser findByEmail(String email){
        return repo.findByEmail(email).orElseThrow(()-> new IllegalStateException("Hubo un error buscando éste email"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .orElseThrow(()-> new IllegalStateException("No se encuentra registrado este email"));
    }

    public void enableAppUser(Long id) {
        repo.enableUser(id);
    }
}
