package com.example.bank.user;

import com.example.bank.account.Account;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public UserBasic getUserBasic(){
        return new UserBasic(this.userId, this.name, this.surname);
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    @JsonIgnore
    public List<Account> getUsersAccount() {
        return usersAccount;
    }

    public void setUsersAccount(List<Account> usersAccount) {
        this.usersAccount = usersAccount;
    }

    @JsonGetter("accountIds")
    public List<Integer> getAccountBasic() {
        return usersAccount.stream()
                .map(account -> account.getAccountId())
                .collect(Collectors.toList());
    }
}
