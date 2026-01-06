package com.amjad.shopapi.benutzer.mapper;


import com.amjad.shopapi.benutzer.dto.BenutzerAnfrage;
import com.amjad.shopapi.benutzer.dto.BenutzerAntwort;
import com.amjad.shopapi.benutzer.modell.Benutzer;
import lombok.experimental.UtilityClass;


@UtilityClass
public class BenutzerMapper {


    // Konvertiert BenutzerAnfrage zu Benutzer Entity

    public static Benutzer toEntity(BenutzerAnfrage anfrage) {
        if (anfrage == null) {
            return null;
        }

        Benutzer benutzer = new Benutzer();
        benutzer.setUsername(anfrage.getUsername());
        benutzer.setEmail(anfrage.getEmail());
        benutzer.setPassword(anfrage.getPassword());
        benutzer.setRole(anfrage.getRole());

        return benutzer;
    }


    // Konvertiert Benutzer Entity zu BenutzerAntwort DTO

    public static BenutzerAntwort toDto(Benutzer benutzer) {
        if (benutzer == null) {
            return null;
        }

        return new BenutzerAntwort(
                benutzer.getId(),
                benutzer.getUsername(),
                benutzer.getEmail(),
                benutzer.getRole()
        );
    }


    // Aktualisiert eine bestehende Entity mit Daten aus BenutzerAnfrage

    public static void updateEntityFromDto(Benutzer benutzer, BenutzerAnfrage anfrage) {
        if (benutzer == null || anfrage == null) {
            return;
        }

        if (anfrage.getUsername() != null) {
            benutzer.setUsername(anfrage.getUsername());
        }
        if (anfrage.getEmail() != null) {
            benutzer.setEmail(anfrage.getEmail());
        }
        if (anfrage.getPassword() != null) {
            benutzer.setPassword(anfrage.getPassword());
        }
        if (anfrage.getRole() != null) {
            benutzer.setRole(anfrage.getRole());
        }
    }
}