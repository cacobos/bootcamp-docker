package com.bootcamp.bill.controller;


import com.bootcamp.bill.service.BillService;
import model.Bill;
import model.BillLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class BillController {
    @Autowired
    private BillService billService;


    @GetMapping("/bills")
    public ResponseEntity<Flux<Bill>> listarBills(){
        return ResponseEntity.ok(billService.findAll());
    }

    @PostMapping("/bills")
    public ResponseEntity<Mono<Bill>> insertar(@RequestBody @Valid Bill bill) throws IOException {
        if(bill.getId()!=null && billService.findById(bill.getId()).blockOptional().isPresent()){
            throw new IOException();
        }
        Mono<Bill> bdBill=billService.save(bill);
        return ResponseEntity.ok(bdBill);
    }

    @GetMapping("/bills/{id}")
    public ResponseEntity<Bill> findById(@PathVariable(name = "id") String id){
        Mono<Bill> bdBill=billService.findById(id);
        return ResponseEntity.ok(bdBill.block());
    }

    @PutMapping("/bills/{id}")
    public ResponseEntity<Mono<Bill>> update(@PathVariable(name = "id") String id,
                                             @RequestBody @Valid Bill bill){
        bill.setId(id);
        Mono<Bill> billbd=billService.save(bill);
        return ResponseEntity.ok(billbd);
    }

    @DeleteMapping("/bills/{id}")
    public ResponseEntity<String> delete(String id){
        if(billService.findById(id).block()!=null){
            return ResponseEntity.ok("Factura eliminada");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/bills/{id}/{status}")
    public ResponseEntity<Bill> findByCustomerAndStatus(@PathVariable("id") int customerId,
                                                        @PathVariable("status") int status){
        return billService.findByCustomerAndStatus(customerId,status);
    }

}
