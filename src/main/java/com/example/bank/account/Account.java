package com.example.bank.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountId;
    private BigDecimal balance;
    private LocalDate creationDate;

    public Account(int accountId, BigDecimal balance, LocalDate creationDate) {
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public Account() {
    }

    public void add(BigDecimal moneyToAdd) {
        this.balance = balance.add(moneyToAdd);
    }

    public void substract(BigDecimal moneyToTake) {
        this.balance = balance.subtract(moneyToTake);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = BigDecimal.valueOf(balance);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
