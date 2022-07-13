package com.gimmenow.deliverynowrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeather {
    private Weather [] weather;
    private String name;
}
