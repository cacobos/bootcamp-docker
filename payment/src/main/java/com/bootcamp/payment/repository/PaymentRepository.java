package com.bootcamp.payment.repository;

import model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
    Flux<Payment> findByStatus(int status);


    Flux<Payment> findByBillId(String id);
}
