package com.amjad.shopapi.benutzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


 // DTO für Benutzer-Erstellung und -Aktualisierung

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BenutzerAnfrage {

    private String username;
    private String email;
    private String password;
    private String role;
}