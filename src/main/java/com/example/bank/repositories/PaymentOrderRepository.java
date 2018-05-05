package com.example.bank.repositories;

import com.example.bank.PaymentOrder.PaymentOrder;
import org.springframework.data.repository.CrudRepository;

public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, Integer> {
}
