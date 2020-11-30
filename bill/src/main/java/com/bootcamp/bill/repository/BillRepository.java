package com.bootcamp.bill.repository;

import model.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BillRepository extends ReactiveMongoRepository<Bill, String> {
    Mono<Bill> findOneByIdClienteAndStatus(String idCliente, int Status);


    ResponseEntity<Bill> findByIdClienteAndStatus(int customerId, int status);
}
