package com.example.springtransaction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name="transactlog")
@Table(name="transactlog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountTo;
    private long amount;
    private Date createdOn;

    public TransactLog (Account accountFrom, Account accountTo, Long amount, Date createdOn){
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.createdOn = createdOn;
    }
}
