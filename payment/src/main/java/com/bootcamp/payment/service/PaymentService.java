package com.bootcamp.payment.service;

import com.bootcamp.payment.repository.PaymentRepository;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import model.Bill;
import model.JsonUtility;
import model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EurekaClient eurekaClient;

    public Flux<Payment> findByStatus(int status){
        return paymentRepository.findByStatus(status);
    }

    public Flux<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public Mono<Payment> findById(String id){
        return paymentRepository.findById(id);
    }

    public void deleteById(String id){
        paymentRepository.deleteById(id);
    }

    public Mono<Payment> insert(Payment payment) {
        if(payment.getId()==null){
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void scheduled(){
        System.out.println("**********Se actualiza el estado de los pagos de las facturas*************");
        updatePaymentsStatus();
        //Actualizamos el status de cada factura en función de los pagos
        updateBillStatus();
    }

    private void updateBillStatus() {
        int nPagos=0;
        Application a=eurekaClient.getApplication("VISIT");
        String url=a.getInstances().get((int)(Math.random()*a.getInstances().size())).getHomePageUrl();
        ResponseEntity<String> response
                = new RestTemplate().getForEntity(url+"/visits/unbilled", String.class);
        List<Bill> billList= JsonUtility.parseJSONList(response.getBody(), Bill[].class);
        for (Bill bill:billList) {
            Iterable<Payment> paymentFlux=paymentRepository.findByBillId(bill.getId()).toIterable();
            Iterator<Payment> it=paymentFlux.iterator();
            while(it.hasNext() && bill.getStatus()!=Bill.IMPAGADA){
                nPagos=0;
                Payment p=it.next();
                if(p.getStatus()==Payment.IMPAGADO){
                    bill.setStatus(Bill.IMPAGADA);
                    guardarBill(bill);
                }else if(p.getStatus()==Payment.PAGADO){
                    nPagos++;
                }
            }
            if(nPagos==bill.getPayment()){
                bill.setStatus(Bill.PAGADA);
                guardarBill(bill);
            }
        }
    }

    private void guardarBill(Bill bill) {
        Application a=eurekaClient.getApplication("BILL");
        String url=a.getInstances().get((int)(Math.random()*a.getInstances().size())).getHomePageUrl();
        ResponseEntity<String> response
                = new RestTemplate().postForEntity(url+"/bills", bill, String.class);
    }

    private void updatePaymentsStatus() {
        Iterable<Payment> payments=paymentRepository.findByStatus(Payment.PENDIENTE).toIterable();
        Iterator<Payment> it=payments.iterator();
        Payment p;
        while(it.hasNext()){
            p=it.next();
            if(p.getExpiring().before(Date.from(Instant.now()))){
                p.setStatus(Payment.IMPAGADO);
                paymentRepository.save(p);
            }
        }
    }


}
