package com.example.bank.user;

import com.example.bank.account.Account;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;
    private String name;
    private String surname;
    private int pesel;
    private List<Account> usersAccount = new LinkedList<>();


    public User(String name, String surname, int pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public User() {
    }

    public void addAccount(Account account) {
        usersAccount.add(account);
    }



}
