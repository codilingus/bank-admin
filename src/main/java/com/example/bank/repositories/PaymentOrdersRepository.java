package com.example.bank.repositories;

import com.example.bank.paymentOrders.PaymentOrders;
import org.springframework.data.repository.CrudRepository;

public interface PaymentOrdersRepository extends CrudRepository<PaymentOrders, Integer> {
}
