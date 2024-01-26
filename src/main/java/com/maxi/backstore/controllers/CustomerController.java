package com.maxi.backstore.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maxi.backstore.entities.Customer;
import com.maxi.backstore.entities.Region;
import com.maxi.backstore.services.customer.CustomerServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerServiceImplementation customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> findAllCustomers(
            @RequestParam(name = "regionId", required = false) Long regionId) {
        List<Customer> customersList = new ArrayList<>();
        if (regionId == null) {
            customersList = customerService.findAllCustomers();
            if (customersList.size() == 0) {
                return ResponseEntity.noContent().build();
            }
        } else {
            Region region = new Region();
            region.setId(regionId);
            customersList = customerService.findByRegion(region);
            if (customersList.size() == 0) {
                log.error("Customer region id {} not found", regionId);
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok().body(customersList);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @GetMapping("/{personalId}")
    public ResponseEntity<Customer> findByPersonalId(@PathVariable String personalId) {
        return ResponseEntity.ok().body(customerService.findByPersonalId(personalId));
    }

    @GetMapping("/region")
    public ResponseEntity<List<Customer>> findByRegion(
            @RequestParam(name = "regionId", required = true) Region region) {
        return ResponseEntity.ok().body(customerService.findByRegion(region));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Success");
    }
}
