package com.bootcamp.customer.controller;

import com.bootcamp.customer.service.CustomerService;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> listAll(){
        return  ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long  id){
        return  ResponseEntity.ok(customerService.findById(id));
    }

    @GetMapping("/customers/state/{state}")
    public ResponseEntity<List<Customer>> findByState(@PathVariable("state") String state){
        return ResponseEntity.ok(customerService.findByState(state));
    }

    @GetMapping("/customers/name/{name}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable("name") String name){
        return ResponseEntity.ok(customerService.findByName(name));
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> insert(@RequestBody @Valid Customer customer){
        if(customer.getId()==null){
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id,
                                           @RequestBody @Valid Customer customer){
        if(customerService.findById(id)!=null){
            customer.setId(id);
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long  id){
        customerService.delete(id);
        return  ResponseEntity.ok("Cliente eliminado con éxito");
    }
}
