package com.example.bank.paymentOrderDto;

import javax.persistence.Entity;
import javax.persistence.Id;


public class PaymentOrderDto {

    private Long userPesel;
    private String amount;

    public PaymentOrderDto(Long userPesel, String amount) {
        this.userPesel = userPesel;
        this.amount = amount;
    }

    public PaymentOrderDto() {
    }

    public Long getUserPesel() {
        return userPesel;
    }

    public void setUserPesel(Long userPesel) {
        this.userPesel = userPesel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
