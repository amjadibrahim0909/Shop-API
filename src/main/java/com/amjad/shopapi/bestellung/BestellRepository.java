package com.amjad.shopapi.bestellung;



import com.amjad.shopapi.bestellung.modell.Bestellung;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BestellRepository extends JpaRepository<Bestellung, Long> {
}
