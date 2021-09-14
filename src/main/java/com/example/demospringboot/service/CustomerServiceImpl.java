package com.example.demospringboot.service;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override public Customer saveCustomer(@RequestBody Customer requestForSave) {
        Customer customer = repository.save(requestForSave);
        return customer;
    }

    @Override
    public List<Customer> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id = " + id));

        if (customer.getIsDeleted()) {
            throw new EntityNotFoundException("Customer was deleted with id = " + id);
        }

        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return repository.findById(customer.getId())
                .map(entity -> {
                    entity.setFirstName(customer.getFirstName());
                    entity.setLastName(customer.getLastName());
                    entity.setPhone(customer.getPhone());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id = " + customer.getId()));
    }

    @Override
    public void removeCustomerById(Long id) {
        repository.findById(id)
                .map(customer -> {
                    customer.setIsDeleted(Boolean.TRUE);
                    return repository.save(customer);
                })
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id = " + id));
    }

    @Override
    public void removeAllUsers() {
        repository.deleteAll();
    }
}
