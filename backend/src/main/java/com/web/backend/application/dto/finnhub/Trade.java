package com.web.backend.application.dto.finnhub;

import lombok.Data;


@Data
public class Trade {
    private Double p;
    private String s;
    private Long t;
    private Double v;
}