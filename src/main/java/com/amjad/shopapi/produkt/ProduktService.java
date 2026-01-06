package com.amjad.shopapi.produkt;

import com.amjad.shopapi.produkt.dto.ProduktAnfrage;
import com.amjad.shopapi.produkt.dto.ProduktAntwort;
import com.amjad.shopapi.produkt.mapper.ProduktMapper;
import com.amjad.shopapi.produkt.modell.Produkt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduktService {

    private final ProduktRepository repository;
    private final ProduktMapper mapper;

    public ProduktAntwort createProduct(ProduktAnfrage anfrage) {
        Produkt produkt = mapper.toEntity(anfrage);
        Produkt savedProdukt = repository.save(produkt);
        return mapper.toAntwort(savedProdukt);
    }

    @Transactional(readOnly = true)
    public List<ProduktAntwort> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toAntwort)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProduktAntwort getProductById(Long id) {
        Produkt produkt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nicht gefunden mit ID: " + id));
        return mapper.toAntwort(produkt);
    }

    public ProduktAntwort updateProduct(Long id, ProduktAnfrage anfrage) {
        Produkt produkt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nicht gefunden mit ID: " + id));

        mapper.updateEntity(anfrage, produkt);
        Produkt updatedProdukt = repository.save(produkt);
        return mapper.toAntwort(updatedProdukt);
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produkt nicht gefunden mit ID: " + id);
        }
        repository.deleteById(id);
    }
}