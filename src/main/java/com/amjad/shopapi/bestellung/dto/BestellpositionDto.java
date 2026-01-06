package com.amjad.shopapi.bestellung.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellpositionDto {

    private Long id;
    private Long productId;
    private int quantity;
    private double price;
}