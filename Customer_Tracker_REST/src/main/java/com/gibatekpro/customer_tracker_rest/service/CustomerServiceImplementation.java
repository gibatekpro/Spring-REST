package com.gibatekpro.customer_tracker_rest.service;

import com.gibatekpro.customer_tracker_rest.dao.CustomerDAO;
import com.gibatekpro.customer_tracker_rest.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;

    @Transactional
    @Override
    public List<CustomerEntity> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Transactional
    @Override
    public void saveCustomer(CustomerEntity customerEntity) {

        customerDAO.saveCustomer(customerEntity);

    }

    @Transactional
    @Override
    public CustomerEntity getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    @Transactional
    @Override
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    @Transactional
    @Override
    public List<CustomerEntity> searchCustomers(String theSearchName) {
        return customerDAO.searchCustomers(theSearchName);
    }
}
