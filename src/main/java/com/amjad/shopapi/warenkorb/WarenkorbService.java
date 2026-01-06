package com.amjad.shopapi.warenkorb;

import com.amjad.shopapi.warenkorb.dto.WarenkorbAnfrage;
import com.amjad.shopapi.warenkorb.dto.WarenkorbAntwort;
import com.amjad.shopapi.warenkorb.mapper.WarenkorbMapper;
import com.amjad.shopapi.warenkorb.modell.Warenkorb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarenkorbService {

    private final WarenkorbRepository repository;
    private final WarenkorbMapper mapper;

    public WarenkorbAntwort createCart(WarenkorbAnfrage anfrage) {
        Warenkorb warenkorb = mapper.toEntity(anfrage);
        Warenkorb savedWarenkorb = repository.save(warenkorb);
        return mapper.toAntwort(savedWarenkorb);
    }

    @Transactional(readOnly = true)
    public WarenkorbAntwort getCartById(Long id) {
        Warenkorb warenkorb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warenkorb nicht gefunden mit ID: " + id));
        return mapper.toAntwort(warenkorb);
    }

    @Transactional(readOnly = true)
    public WarenkorbAntwort getCartByUserId(Long userId) {
        // Hier müsste ein Repository-Methode hinzugefügt werden
        // Annahme: Jeder User hat genau einen Warenkorb
        return repository.findByUserId(userId)
                .map(mapper::toAntwort)
                .orElseThrow(() -> new RuntimeException("Warenkorb nicht gefunden für User ID: " + userId));
    }

    public WarenkorbAntwort updateCart(Long id, WarenkorbAnfrage anfrage) {
        Warenkorb warenkorb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warenkorb nicht gefunden mit ID: " + id));

        mapper.updateEntity(anfrage, warenkorb);
        Warenkorb updatedWarenkorb = repository.save(warenkorb);
        return mapper.toAntwort(updatedWarenkorb);
    }


    public void deleteCart(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Warenkorb nicht gefunden mit ID: " + id);
        }
        repository.deleteById(id);
    }

    public void removeItemFromCart(Long cartId, Long productId) {
        Warenkorb warenkorb = repository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Warenkorb nicht gefunden mit ID: " + cartId));

        warenkorb.getItems().removeIf(position -> position.getProductId().equals(productId));
        repository.save(warenkorb);
    }
}