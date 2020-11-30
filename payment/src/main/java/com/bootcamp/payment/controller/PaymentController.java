package com.bootcamp.payment.controller;

import com.bootcamp.payment.service.PaymentService;
import model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<Flux<Payment>> listAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Mono<Payment>> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping("/payments")
    public ResponseEntity<Mono<Payment>> insert(@RequestBody @Valid Payment payment){
        if(payment.getId()==null) {
            return ResponseEntity.ok(paymentService.insert(payment));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<Mono<Payment>> update(@RequestBody @Valid Payment payment,
                                @PathVariable("id") String id){
        if(paymentService.findById(id).hasElement().block()){
            return ResponseEntity.ok(paymentService.insert(payment));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<String> insert(@PathVariable("id") String id){
        if(paymentService.findById(id).hasElement().block()) {
            paymentService.deleteById(id);
            return ResponseEntity.ok("Se ha eliminado el pago");
        }
        return ResponseEntity.badRequest().build();
    }
}
