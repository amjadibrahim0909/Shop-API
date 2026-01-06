package com.amjad.shopapi.benutzer;


import com.amjad.shopapi.benutzer.modell.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository für Benutzer.
 */
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

    Optional<Benutzer> findByEmail(String email);
}
