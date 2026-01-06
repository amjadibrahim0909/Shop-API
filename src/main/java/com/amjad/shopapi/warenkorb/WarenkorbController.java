package com.amjad.shopapi.warenkorb;

import com.amjad.shopapi.warenkorb.dto.WarenkorbAnfrage;
import com.amjad.shopapi.warenkorb.dto.WarenkorbAntwort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class WarenkorbController {

    private final WarenkorbService service;

    @PostMapping
    public ResponseEntity<WarenkorbAntwort> createCart(@Valid @RequestBody WarenkorbAnfrage anfrage) {
        WarenkorbAntwort antwort = service.createCart(anfrage);
        return ResponseEntity.status(HttpStatus.CREATED).body(antwort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarenkorbAntwort> getCartById(@PathVariable Long id) {
        WarenkorbAntwort antwort = service.getCartById(id);
        return ResponseEntity.ok(antwort);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<WarenkorbAntwort> getCartByUserId(@PathVariable Long userId) {
        WarenkorbAntwort antwort = service.getCartByUserId(userId);
        return ResponseEntity.ok(antwort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarenkorbAntwort> updateCart(@PathVariable Long id,
                                                       @Valid @RequestBody WarenkorbAnfrage anfrage) {
        WarenkorbAntwort antwort = service.updateCart(id, anfrage);
        return ResponseEntity.ok(antwort);
    }


    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId,
                                                   @PathVariable Long productId) {
        service.removeItemFromCart(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        service.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}