package com.gibatekpro.customer_tracker_rest.dao;

import com.gibatekpro.customer_tracker_rest.entity.CustomerEntity;

import java.util.List;

public interface CustomerDAO {
    List<CustomerEntity> getCustomers();

    void saveCustomer(CustomerEntity customerEntity);

    CustomerEntity getCustomer(int id);

    void deleteCustomer(int id);

    List<CustomerEntity> searchCustomers(String theSearchName);
}
