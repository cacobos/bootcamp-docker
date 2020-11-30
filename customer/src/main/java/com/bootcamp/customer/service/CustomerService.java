package com.bootcamp.customer.service;

import com.bootcamp.customer.repository.AddressRepository;
import com.bootcamp.customer.repository.CustomerRepository;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import model.Address;
import model.Bill;
import model.Customer;
import model.JsonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EurekaClient eurekaClient;

    public List<Customer> findAll(){
        List<Customer> customers=customerRepository.findAll();
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).setAddressList(addressRepository.findByCustomerId(customers.get(i).getId()));
        }
        return customers;
    }

    public Customer findById(Long id){
        if(customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            customer.setAddressList(addressRepository.findByCustomerId(id));
            return customer;
        }else {
            return null;
        }
    }



    public List<Customer> findByState(String state){
        List<Address> addressList=addressRepository.findByStateContains(state);
        List<Customer> customers=new ArrayList<>();
        for (int i = 0; i < addressList.size(); i++) {
            customers.add(customerRepository.findById(addressList.get(i).getCustomerId()).get());
        }
        return customers;
    }

    public List<Customer> findByName(String name){
        return customerRepository.findByNameContains(name);
    }



    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    //La tarea programada se ejecutará una vez al día a las 0:02
    @Scheduled(cron = "0 * 0 02 * ?")
    public void updateStatus(){
        List<Customer>customerList=customerRepository.findAll();
        List<Bill> billList;
        for (Customer customer:customerList) {
            Application a=eurekaClient.getApplication("BILL");
            String url=a.getInstances().get((int)(Math.random()*a.getInstances().size())).getHomePageUrl();
            ResponseEntity<String> response
                    = new RestTemplate().getForEntity(url+"/bill", String.class, customer.getId(), Bill.IMPAGADA);
            billList= JsonUtility.parseJSONList(response.getBody(),Bill[].class);
            if(billList.size()>0){
                customer.setStatus(Customer.IMPAGADO);
                customerRepository.save(customer);
            }else{
                response
                        = new RestTemplate().getForEntity(url+"/bill", String.class, customer.getId(), Bill.PENDIENTE_PAGO);
                billList= JsonUtility.parseJSONList(response.getBody(),Bill[].class);
                if(billList.size()>0) {
                    customer.setStatus(Customer.FACTURA_PENDIENTE);
                    customerRepository.save(customer);
                }
                else{
                    customer.setStatus(Customer.SIN_PENDIENTES);
                    customerRepository.save(customer);
                }
            }
        }
    }
}
