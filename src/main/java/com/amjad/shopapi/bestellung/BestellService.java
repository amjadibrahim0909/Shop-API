package com.amjad.shopapi.bestellung;

import com.amjad.shopapi.bestellung.dto.BestellAnfrage;
import com.amjad.shopapi.bestellung.dto.BestellAntwort;
import com.amjad.shopapi.bestellung.mapper.BestellMapper;
import com.amjad.shopapi.bestellung.modell.Bestellung;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BestellService {

    private final BestellRepository repository;

    public BestellAntwort createOrder(BestellAnfrage anfrage) {
        Bestellung bestellung = BestellMapper.toEntity(anfrage);
        Bestellung gespeicherteBestellung = repository.save(bestellung);
        return BestellMapper.toDto(gespeicherteBestellung);
    }

    public List<BestellAntwort> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(BestellMapper::toDto)
                .collect(Collectors.toList());
    }

    public BestellAntwort getOrderById(Long id) {
        return repository.findById(id)
                .map(BestellMapper::toDto)
                .orElse(null);
    }
}