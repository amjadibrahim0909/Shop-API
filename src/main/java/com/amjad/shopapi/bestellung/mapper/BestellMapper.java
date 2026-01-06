package com.amjad.shopapi.bestellung.mapper;


import com.amjad.shopapi.bestellung.dto.BestellAnfrage;
import com.amjad.shopapi.bestellung.dto.BestellAntwort;
import com.amjad.shopapi.bestellung.dto.BestellpositionDto;
import com.amjad.shopapi.bestellung.modell.Bestellung;
import com.amjad.shopapi.bestellung.modell.Bestellposition;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class BestellMapper {


     // Konvertiert BestellAnfrage zu Bestellung Entity

    public static Bestellung toEntity(BestellAnfrage anfrage) {
        if (anfrage == null) {
            return null;
        }

        Bestellung bestellung = new Bestellung();
        bestellung.setUserId(anfrage.getUserId());

        if (anfrage.getOrderItems() != null) {
            List<Bestellposition> positionen = anfrage.getOrderItems().stream()
                    .map(BestellMapper::toBestellpositionEntity)
                    .collect(Collectors.toList());
            bestellung.setOrderItems(positionen);

            // Berechne Gesamtpreis
            double totalPrice = positionen.stream()
                    .mapToDouble(p -> p.getPrice() * p.getQuantity())
                    .sum();
            bestellung.setTotalPrice(totalPrice);
        }

        return bestellung;
    }


    // Konvertiert Bestellung Entity zu BestellAntwort DTO

    public static BestellAntwort toDto(Bestellung bestellung) {
        if (bestellung == null) {
            return null;
        }

        List<BestellpositionDto> positionDtos = null;
        if (bestellung.getOrderItems() != null) {
            positionDtos = bestellung.getOrderItems().stream()
                    .map(BestellMapper::toBestellpositionDto)
                    .collect(Collectors.toList());
        }

        return new BestellAntwort(
                bestellung.getId(),
                bestellung.getUserId(),
                bestellung.getTotalPrice(),
                positionDtos
        );
    }


     // Konvertiert BestellpositionDto zu Bestellposition Entity

    private static Bestellposition toBestellpositionEntity(BestellpositionDto dto) {
        if (dto == null) {
            return null;
        }

        Bestellposition position = new Bestellposition();
        position.setId(dto.getId());
        position.setProductId(dto.getProductId());
        position.setQuantity(dto.getQuantity());
        position.setPrice(dto.getPrice());

        return position;
    }


    //  Konvertiert Bestellposition Entity zu BestellpositionDto

    private static BestellpositionDto toBestellpositionDto(Bestellposition position) {
        if (position == null) {
            return null;
        }

        return new BestellpositionDto(
                position.getId(),
                position.getProductId(),
                position.getQuantity(),
                position.getPrice()
        );
    }
}