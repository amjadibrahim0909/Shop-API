package com.amjad.shopapi.benutzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BenutzerAntwort {

    private Long id;
    private String username;
    private String email;
    private String role;
}