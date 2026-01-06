package com.amjad.shopapi.warenkorb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WarenkorbPositionAnfrage {

    @NotNull(message = "Product ID ist erforderlich")
    private Long productId;

    @NotNull(message = "Menge ist erforderlich")
    @Min(value = 1, message = "Menge muss mindestens 1 sein")
    private Integer quantity;
}