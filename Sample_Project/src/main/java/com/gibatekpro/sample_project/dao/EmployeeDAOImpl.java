package com.gibatekpro.sample_project.dao;

import com.gibatekpro.sample_project.entity.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    //define field for EntityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getEmployees() {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "from Employee", Employee.class
        );

        return query.getResultList();
    }

    @Override
    public Employee findEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        return session.get(Employee.class, id);
    }

    @Override
    public void addEmployee(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);

    }

    @Override
    public void deleteEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "delete from Employee where id=:id"
        );
        query.setParameter("id", id);

        query.executeUpdate();

    }
}
