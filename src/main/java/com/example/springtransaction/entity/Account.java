package com.example.springtransaction.entity;

import com.example.springtransaction.enums.AccountState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="account")
@Entity(name="account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private Long balance;
    private AccountState state;
    public Account(String owner, Long balance) {
        this.owner = owner;
        this.balance = balance;
        this.state = AccountState.ACTIVE;
    }
}
