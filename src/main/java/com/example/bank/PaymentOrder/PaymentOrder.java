package com.example.bank.PaymentOrder;

import com.example.bank.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PaymentOrder {


    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    private LocalDateTime modificationDate;
    @ManyToOne
    private User user;
    private Status status;


    public PaymentOrder() {
    }

    public PaymentOrder(BigDecimal amount, LocalDateTime modificationDate, User user, Status status ) {
        this.id = id;
        this.amount = amount;
        this.modificationDate = modificationDate;
        this.user = user;
        this.status = status;

    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
