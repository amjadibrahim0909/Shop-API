package com.amjad.shopapi.warenkorb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarenkorbAntwort {
    private Long id;
    private Long userId;
    private List<WarenkorbPositionAntwort> items;
    private Double gesamtpreis;
}