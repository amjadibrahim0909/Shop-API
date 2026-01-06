package com.amjad.shopapi.warenkorb;

import com.amjad.shopapi.warenkorb.modell.Warenkorb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarenkorbRepository extends JpaRepository<Warenkorb, Long> {

    Optional<Warenkorb> findByUserId(Long userId);
}