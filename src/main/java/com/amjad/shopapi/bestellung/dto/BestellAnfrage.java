package com.amjad.shopapi.bestellung.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellAnfrage {

    private Long userId;
    private List<BestellpositionDto> orderItems;
}