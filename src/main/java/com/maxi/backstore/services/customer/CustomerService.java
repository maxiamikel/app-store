package com.maxi.backstore.services.customer;

import java.util.List;

import com.maxi.backstore.entities.Customer;
import com.maxi.backstore.entities.Region;

public interface CustomerService {
    public Customer createCustomer(Customer customer);

    public Customer findByCustomerId(Long id);

    public Customer findByPersonalId(String personalId);

    public List<Customer> findByRegion(Region region);

    public List<Customer> findAllCustomers();

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Long id);

}
