package com.example.bank.validation;

import com.example.bank.account.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransferValidator {

    public Boolean isThereAnyMoney(Account account){
        return (account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }
}
