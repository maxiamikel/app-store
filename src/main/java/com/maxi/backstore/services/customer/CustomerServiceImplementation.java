package com.maxi.backstore.services.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxi.backstore.entities.Customer;
import com.maxi.backstore.entities.Region;
import com.maxi.backstore.enums.CustomerStatus;
import com.maxi.backstore.repositories.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private boolean existCustomerByPersonalNubmer(String personalNumber) {
        Customer customer = customerRepository.findByPersonalId(personalNumber);
        if (customer != null) {
            return true;
        }
        return false;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByPersonalId(customer.getPersonalId());
        if (customerDB != null) {
            return customerDB;
        } else {
            customer.setId(null);
            customer.setStatus(CustomerStatus.ACTIVATE);
            customerDB = customerRepository.save(customer);
            return customerDB;
        }
    }

    @Override
    public Customer findByCustomerId(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer id not found"));
    }

    @Override
    public Customer findByPersonalId(String personalId) {
        if (!this.existCustomerByPersonalNubmer(personalId)) {
            throw new RuntimeException("The customer personal number [" + personalId + "] not found");
        }
        Customer customer = customerRepository.findByPersonalId(personalId);
        return customer;
    }

    @Override
    public List<Customer> findByRegion(Region region) {
        List<Customer> customersList = customerRepository.findByRegion(region);
        return customersList;
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customersList = customerRepository.findAllCustomers();
        if (customersList.size() == 0) {
            throw new RuntimeException("No customer (s) to show");
        }
        return customersList;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = this.findByCustomerId(customer.getId());
        if (!customerDB.getPersonalId().equalsIgnoreCase(customer.getPersonalId())) {
            throw new RuntimeException(
                    "The Personal Id is not matches");
        } else {
            customerDB.setEmail(customer.getEmail());
            customerDB.setFoneNumber(customer.getEmail());
            customerDB.setName(customer.getName());
            customerDB.setRegion(customer.getRegion());

            customerDB = customerRepository.saveAndFlush(customerDB);
            return customerDB;
        }

    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = this.findByCustomerId(id);
        customer.setStatus(CustomerStatus.DESACTIVATE);
        customerRepository.saveAndFlush(customer);

    }

}
