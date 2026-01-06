package com.amjad.shopapi.benutzer;

import com.amjad.shopapi.benutzer.dto.BenutzerAnfrage;
import com.amjad.shopapi.benutzer.dto.BenutzerAntwort;

import com.amjad.shopapi.benutzer.mapper.BenutzerMapper;
import com.amjad.shopapi.benutzer.modell.Benutzer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Geschäftslogik für Benutzer mit DTO-Unterstützung
 */
@Service
@RequiredArgsConstructor
public class BenutzerService {

    private final BenutzerRepository repository;

    public BenutzerAntwort createUser(BenutzerAnfrage anfrage) {
        Benutzer benutzer = BenutzerMapper.toEntity(anfrage);
        Benutzer gespeicherterBenutzer = repository.save(benutzer);
        return BenutzerMapper.toDto(gespeicherterBenutzer);
    }

    public List<BenutzerAntwort> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(BenutzerMapper::toDto)
                .collect(Collectors.toList());
    }

    public BenutzerAntwort getUserById(Long id) {
        return repository.findById(id)
                .map(BenutzerMapper::toDto)
                .orElse(null);
    }

    public BenutzerAntwort getUserByEmail(String email) {
        return repository.findByEmail(email)
                .map(BenutzerMapper::toDto)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public BenutzerAntwort updateUser(Long id, BenutzerAnfrage anfrage) {
        return repository.findById(id)
                .map(benutzer -> {
                    BenutzerMapper.updateEntityFromDto(benutzer, anfrage);
                    Benutzer aktualisierterBenutzer = repository.save(benutzer);
                    return BenutzerMapper.toDto(aktualisierterBenutzer);
                })
                .orElse(null);
    }
}