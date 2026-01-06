package com.amjad.shopapi.warenkorb.mapper;

import com.amjad.shopapi.produkt.ProduktService;
import com.amjad.shopapi.warenkorb.dto.*;
import com.amjad.shopapi.warenkorb.modell.Warenkorb;
import com.amjad.shopapi.warenkorb.modell.WarenkorbPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WarenkorbMapper {

    private final ProduktService produktService;

    public Warenkorb toEntity(WarenkorbAnfrage anfrage) {
        if (anfrage == null) {
            return null;
        }

        Warenkorb warenkorb = Warenkorb.builder()
                .userId(anfrage.getUserId())
                .build();

        if (anfrage.getItems() != null) {
            List<WarenkorbPosition> positionen = anfrage.getItems().stream()
                    .map(item -> toPositionEntity(item, warenkorb))
                    .collect(Collectors.toList());
            warenkorb.setItems(positionen);
        }

        return warenkorb;
    }

    private WarenkorbPosition toPositionEntity(WarenkorbPositionAnfrage anfrage, Warenkorb warenkorb) {
        return WarenkorbPosition.builder()
                .productId(anfrage.getProductId())
                .quantity(anfrage.getQuantity())
                .build();
    }

    public WarenkorbAntwort toAntwort(Warenkorb warenkorb) {
        if (warenkorb == null) {
            return null;
        }

        List<WarenkorbPositionAntwort> positionAntworten = warenkorb.getItems().stream()
                .map(this::toPositionAntwort)
                .collect(Collectors.toList());

        Double gesamtpreis = positionAntworten.stream()
                .mapToDouble(WarenkorbPositionAntwort::getPositionPreis)
                .sum();

        return WarenkorbAntwort.builder()
                .id(warenkorb.getId())
                .userId(warenkorb.getUserId())
                .items(positionAntworten)
                .gesamtpreis(gesamtpreis)
                .build();
    }

    private WarenkorbPositionAntwort toPositionAntwort(WarenkorbPosition position) {
        // Hole Produktinformationen vom ProduktService
        var produktAntwort = produktService.getProductById(position.getProductId());

        Double einzelpreis = produktAntwort != null ? produktAntwort.getPrice() : 0.0;
        Double positionPreis = einzelpreis * position.getQuantity();

        return WarenkorbPositionAntwort.builder()
                .id(position.getId())
                .productId(position.getProductId())
                .quantity(position.getQuantity())
                .produktName(produktAntwort != null ? produktAntwort.getName() : "Unbekannt")
                .einzelpreis(einzelpreis)
                .positionPreis(positionPreis)
                .build();
    }

    public void updateEntity(WarenkorbAnfrage anfrage, Warenkorb warenkorb) {
        if (anfrage == null || warenkorb == null) {
            return;
        }

        // Lösche alle vorhandenen Positionen
        warenkorb.getItems().clear();

        // Füge neue Positionen hinzu
        if (anfrage.getItems() != null) {
            List<WarenkorbPosition> neuePositionen = anfrage.getItems().stream()
                    .map(item -> toPositionEntity(item, warenkorb))
                    .collect(Collectors.toList());
            warenkorb.getItems().addAll(neuePositionen);
        }
    }
}