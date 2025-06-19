package com.example.Akhil.project.DTOClasses;

import com.example.Akhil.project.Tables.Portfolio;
import com.example.Akhil.project.Tables.Stocks;
import com.example.Akhil.project.Tables.User;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StocksDTO {
    private Integer stock_id;
    private String stock_sym;
    private String name;
    private Integer day_before_price;
    private Long market_cap;
    private Integer current_price;
    private List<Portfolio> portfolios;
}
