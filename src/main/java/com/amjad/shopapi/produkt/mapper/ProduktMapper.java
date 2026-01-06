package com.amjad.shopapi.produkt.mapper;

import com.amjad.shopapi.produkt.dto.ProduktAnfrage;
import com.amjad.shopapi.produkt.dto.ProduktAntwort;
import com.amjad.shopapi.produkt.modell.Produkt;
import org.springframework.stereotype.Component;

@Component
public class ProduktMapper {

    public ProduktAntwort toAntwort(Produkt produkt) {
        if (produkt == null) {
            return null;
        }

        return ProduktAntwort.builder()
                .id(produkt.getId())
                .name(produkt.getName())
                .description(produkt.getDescription())
                .price(produkt.getPrice())
                .build();
    }

    public Produkt toEntity(ProduktAnfrage anfrage) {
        if (anfrage == null) {
            return null;
        }

        return Produkt.builder()
                .name(anfrage.getName())
                .description(anfrage.getDescription())
                .price(anfrage.getPrice())
                .build();
    }

    public void updateEntity(ProduktAnfrage anfrage, Produkt produkt) {
        if (anfrage == null || produkt == null) {
            return;
        }

        produkt.setName(anfrage.getName());
        produkt.setDescription(anfrage.getDescription());
        produkt.setPrice(anfrage.getPrice());
    }
}