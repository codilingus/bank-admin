package com.example.bank.account;

import java.time.LocalDate;

public class Account {

    private int accountId;
    private double balance;
    private LocalDate creationDate;

    public Account(int accountId) {
        this.accountId = accountId;
        this.creationDate = LocalDate.now();
    }
}
