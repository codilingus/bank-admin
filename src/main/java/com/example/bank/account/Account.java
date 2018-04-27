package com.example.bank.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private LocalDate creationDate;

    public Account(int accountId, double balance, LocalDate creationDate) {
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
