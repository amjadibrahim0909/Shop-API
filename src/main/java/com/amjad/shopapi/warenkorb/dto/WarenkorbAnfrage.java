package com.amjad.shopapi.warenkorb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class WarenkorbAnfrage {

    @NotNull(message = "User ID ist erforderlich")
    private Long userId;

    @Valid
    private List<WarenkorbPositionAnfrage> items;
}