package com.amjad.shopapi.bestellung.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellAntwort {

    private Long id;
    private Long userId;
    private double totalPrice;
    private List<BestellpositionDto> orderItems;
}
