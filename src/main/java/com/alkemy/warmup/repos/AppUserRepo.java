package com.alkemy.warmup.repos;

import com.alkemy.warmup.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE AppUser a SET a.enabled = TRUE WHERE email = ?1 ")
    void enableAppUser(String email);
}
