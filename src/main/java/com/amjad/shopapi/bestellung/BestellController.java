package com.amjad.shopapi.bestellung;

import com.amjad.shopapi.bestellung.dto.BestellAnfrage;
import com.amjad.shopapi.bestellung.dto.BestellAntwort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für Bestellungen mit DTO-Unterstützung
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class BestellController {

    private final BestellService service;

    @PostMapping
    public ResponseEntity<BestellAntwort> createOrder(@RequestBody BestellAnfrage anfrage) {
        BestellAntwort antwort = service.createOrder(anfrage);
        return ResponseEntity.status(HttpStatus.CREATED).body(antwort);
    }

    @GetMapping
    public ResponseEntity<List<BestellAntwort>> getAllOrders() {
        List<BestellAntwort> orders = service.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BestellAntwort> getOrderById(@PathVariable Long id) {
        BestellAntwort antwort = service.getOrderById(id);
        if (antwort == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(antwort);
    }
}