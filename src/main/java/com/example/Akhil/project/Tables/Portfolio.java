package com.stport.spm.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "PORTFOLIO")
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer portfolio_id;
    private Integer shares;
    private String stock_id;
    private Long current_price;
    private Long cost_basis;
    private Integer yield;
    @OneToMany(mappedBy = "portfolio")
    private List<User> user;
    @ManyToMany(mappedBy = "portfolios")
    private List<Stocks> stocks;
    
}
