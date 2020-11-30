package com.bootcamp.customer.repository;

import model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByCustomerId(Long idCostumer);
    List<Address> findByStateContains(String state);
}
