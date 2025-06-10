package com.stport.spm.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "STOCKS_DATA")
@Data
public class Stocks {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stock_id;
    private String stock_sym;
    private String name;
    private Integer day_before_price;
    private Long market_cap;
    private Integer current_price;
    @ManyToMany
    private List<Portfolio> portfolios;
}
