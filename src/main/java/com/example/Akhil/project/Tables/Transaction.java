package com.stport.spm.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "TRANSACTIONS")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer t_id;
    private String stock_sym;
    private String type;
    private Integer qty;
    private Long price;
    private Timestamp timestamp_t;
    @OneToMany(mappedBy = "transaction")
    private List<User> users;
}
