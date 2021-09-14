package com.example.demospringboot.service;

import com.example.demospringboot.domain.Customer;

import java.util.List;


public interface CustomerService {

    /**
     * Save new employee.
     *
     * @param requestForSave {@link Customer}.
     * @return DTO representation for saved customer.
     */
    Customer saveCustomer(Customer requestForSave);

    List<Customer> getAllUsers();

    Customer getCustomerById(final Long id);

    Customer updateCustomer(final Customer customer);

    void removeCustomerById(final Long id);

    void removeAllUsers();

}
