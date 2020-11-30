package com.bootcamp.customer.repository;

import model.Address;
import model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    List<Customer> findByNameContains(String name);
    void deleteById(Long id);
    //List<Customer> findByProvinciaLike(String provincia);
}
