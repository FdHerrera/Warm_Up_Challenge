package com.alkemy.warmup.repos;

import com.alkemy.warmup.auth.registration.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query (value = "UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE token = ?1")
    void updateConfirmedAt(String token, LocalDateTime now);

}
