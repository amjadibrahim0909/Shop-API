package com.amjad.shopapi.warenkorb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarenkorbPositionAntwort {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String produktName;
    private Double einzelpreis;
    private Double positionPreis;
}