package com.alkemy.warmup.auth.service;

import com.alkemy.warmup.auth.model.AppUser;
import com.alkemy.warmup.auth.model.RegistrationRequest;
import com.alkemy.warmup.repos.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final AppUserRepo repo;

    public AppUser signUpUser(AppUser user){
        boolean userExists = repo.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("Éste email ya está registrado");
        }
        String encodedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPass);
        repo.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .orElseThrow(()-> new IllegalStateException("No se encuentra registrado este email"));
    }
}
