package com.stport.spm.DTOClasses;

import com.stport.spm.Tables.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Integer t_id;
    private String stock_sym;
    private String type;
    private Integer qty;
    private Long price;
    private Timestamp timestamp_t;
    private List<User> users;
}
