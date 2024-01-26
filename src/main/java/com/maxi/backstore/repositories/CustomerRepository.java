package com.maxi.backstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.maxi.backstore.entities.Customer;
import com.maxi.backstore.entities.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByPersonalId(String personalId);

    public List<Customer> findByRegion(Region region);

    @Query("SELECT c FROM Customer c")
    public List<Customer> findAllCustomers();
}
