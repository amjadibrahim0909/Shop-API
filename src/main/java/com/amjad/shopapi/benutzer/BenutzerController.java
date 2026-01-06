package com.amjad.shopapi.benutzer;

import com.amjad.shopapi.benutzer.dto.BenutzerAnfrage;
import com.amjad.shopapi.benutzer.dto.BenutzerAntwort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class BenutzerController {

    private final BenutzerService service;

    public BenutzerController(BenutzerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BenutzerAntwort> createUser(@RequestBody BenutzerAnfrage anfrage) {
        BenutzerAntwort antwort = service.createUser(anfrage);
        return ResponseEntity.status(HttpStatus.CREATED).body(antwort);
    }

    @GetMapping
    public ResponseEntity<List<BenutzerAntwort>> getAllUsers() {
        List<BenutzerAntwort> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenutzerAntwort> getUserById(@PathVariable Long id) {
        BenutzerAntwort antwort = service.getUserById(id);
        if (antwort == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(antwort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BenutzerAntwort> updateUser(
            @PathVariable Long id,
            @RequestBody BenutzerAnfrage anfrage) {
        BenutzerAntwort antwort = service.updateUser(id, anfrage);
        if (antwort == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(antwort);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}