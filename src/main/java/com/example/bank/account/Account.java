package com.example.bank.account;

import java.time.LocalDate;

public class Account {

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
