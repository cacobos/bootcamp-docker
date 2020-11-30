package com.bootcamp.visit.repository;


import model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface VisitRepository extends JpaRepository<Visit, Long>{
    List<Visit> findAll();
    List<Visit> findByIdCustomer(String idCliente);
    List<Visit> findByStatus(int status);
    List<Visit> findByStatusAndDateBefore(int status, Date date);
    List<Visit> findByIdCustomerAndStatus(String customerId, int status);
    List<Visit> findByIdBill(String billLineId);
    List<Visit> findByStatusOrStatusIsNull(int status);
    void deleteById(String id);
}
