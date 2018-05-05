package com.example.bank.paymentOrders;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaymentOrders {
    @Id
    private Long userPesel;
    private String amount;

    public PaymentOrders(Long userPesel, String amount) {
        this.userPesel = userPesel;
        this.amount = amount;
    }

    public PaymentOrders() {
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
