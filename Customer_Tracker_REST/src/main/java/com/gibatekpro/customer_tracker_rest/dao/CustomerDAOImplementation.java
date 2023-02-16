package com.gibatekpro.customer_tracker_rest.dao;

import com.gibatekpro.customer_tracker_rest.entity.CustomerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO{

    //need to inject sessionFactory
    /**
     * In a case of using multiple DataSources (DB),
     * each will use a different Session Factory,
     * so it is necessary to use @Qualifier to specify
     * the particular bean to be injected.
     **/
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * In a case of using multiple DataSources (DB),
     * it is also necessary to specify and inject
     * the Transaction manager to be used.
     * In this case: @Transactional("myTransactionManager")
     * <p>
     * The transaction is being done in the service, so it's
     * not necessary here
     **/
    @Override
    public List<CustomerEntity> getCustomers() {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //create a query
        Query<CustomerEntity> query = session.createQuery("from CustomerEntity order by lastName", CustomerEntity.class);

        //execute query and get result list
        List<CustomerEntity> customerEntityList = query.getResultList();

        //return the results
        return customerEntityList;
    }

    @Override
    public void saveCustomer(CustomerEntity customerEntity) {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //save or update the customer
        //If a primary key or id is already available,
        //Hibernate will update the entity
        session.saveOrUpdate(customerEntity);

    }

    @Override
    public CustomerEntity getCustomer(int id) {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //get customer from ID
        return session.get(CustomerEntity.class, id);

    }

    @Override
    public void deleteCustomer(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<CustomerEntity> query = session.createQuery("delete from CustomerEntity where id=:id");

        query.setParameter("id", id);

        query.executeUpdate();


    }

    @Override
    public List<CustomerEntity> searchCustomers(String theSearchName) {

        Session session = sessionFactory.getCurrentSession();

        Query<CustomerEntity> query = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            query = session.createQuery("from CustomerEntity where lower(lastName) like :theSearchName or lower(firstName) like:theSearchName order by lastName", CustomerEntity.class);

//            We also make use of the "like" clause and the "%" wildcard characters.
//            This will allow us to search for substrings. For example, if we have
//            customers with last name of "Patel", "Patterson" ... then we can search
//            for "Pat" and it will match on those names.
            //We are making both the search parameter and the db parameter lower case
            query.setParameter("theSearchName", "%" + theSearchName.toLowerCase() + "%");

        }else {
            // theSearchName is empty ... so just get all customers
            query = session.createQuery("from CustomerEntity order by lastName", CustomerEntity.class);
        }

        List<CustomerEntity> customers = query.getResultList();
        return customers;
    }

}
