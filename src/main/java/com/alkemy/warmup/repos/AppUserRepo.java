package com.alkemy.warmup.repos;

import com.alkemy.warmup.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Query("UPDATE AppUser a SET enabled = TRUE WHERE id = ?1")
    void enableUser(Long id);
}
