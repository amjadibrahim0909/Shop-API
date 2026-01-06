package com.amjad.shopapi.produkt;



import com.amjad.shopapi.produkt.modell.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProduktRepository extends JpaRepository<Produkt, Long> {
}
