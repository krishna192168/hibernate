package com.krishna.hibernate.service;

import com.krishna.hibernate.dao.CustomerDAO;
import com.krishna.hibernate.model.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @PostConstruct
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setName("raj");
        customerDAO.merge(customer);
    }
}
