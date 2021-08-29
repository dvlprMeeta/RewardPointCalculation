package com.infogain.retailer.service.impl;

import com.infogain.retailer.entity.Customer;
import com.infogain.retailer.repository.CustomerRepository;
import com.infogain.retailer.service.RewardSecurityCustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RewardSecurityCustomerServiceImpl implements RewardSecurityCustomerServiceI {
    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customerById = customerRepository.findById(customerId);
        Customer customer=null;
        if(customerById.isPresent()){
            customer = customerById.get();
        }
        return customer;
    }
}
