package com.amjad.shopapi.sicherheit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

 // Hilfsklasse für JWT-Erstellung und -Validierung.

@Component
public class JwtUtils {

    private final String geheimSchluessel = "geheimesSchluesselWort";
    private final long ablaufZeitMs = 86400000; // 1 Tag


     // Erstellt ein JWT für einen Benutzer.

    public String tokenErstellen(String benutzername) {
        return Jwts.builder()
                .setSubject(benutzername)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ablaufZeitMs))
                .signWith(SignatureAlgorithm.HS256, geheimSchluessel)
                .compact();
    }


      // Extrahiert den Benutzernamen aus dem Token.

    public String benutzernameAusToken(String token) {
        return tokenLesen(token).getSubject();
    }


     // Prüft, ob ein Token gültig ist.

    public boolean tokenGueltig(String token) {
        try {
            tokenLesen(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims tokenLesen(String token) {
        return Jwts.parser()
                .setSigningKey(geheimSchluessel)
                .parseClaimsJws(token)
                .getBody();
    }
}
