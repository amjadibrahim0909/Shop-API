package com.amjad.shopapi.produkt;

import com.amjad.shopapi.produkt.dto.ProduktAnfrage;
import com.amjad.shopapi.produkt.dto.ProduktAntwort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProduktController {

    private final ProduktService service;

    @PostMapping
    public ResponseEntity<ProduktAntwort> createProduct(@Valid @RequestBody ProduktAnfrage anfrage) {
        ProduktAntwort antwort = service.createProduct(anfrage);
        return ResponseEntity.status(HttpStatus.CREATED).body(antwort);
    }

    @GetMapping
    public ResponseEntity<List<ProduktAntwort>> getAllProducts() {
        List<ProduktAntwort> produkte = service.getAllProducts();
        return ResponseEntity.ok(produkte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduktAntwort> getProductById(@PathVariable Long id) {
        ProduktAntwort antwort = service.getProductById(id);
        return ResponseEntity.ok(antwort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduktAntwort> updateProduct(@PathVariable Long id,
                                                        @Valid @RequestBody ProduktAnfrage anfrage) {
        ProduktAntwort antwort = service.updateProduct(id, anfrage);
        return ResponseEntity.ok(antwort);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}