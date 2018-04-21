package com.example.bank.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountId;
    private double balance;
    private LocalDate creationDate;

    public Account(int accountId, double balance, LocalDate creationDate) {
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account() {
    }
}
