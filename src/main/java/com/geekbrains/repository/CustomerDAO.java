package com.geekbrains.repository;

import com.geekbrains.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDAO implements CRUDRepository<Customer, Long>{
    private SessionFactory sessionFactory;

    public CustomerDAO() {
        sessionFactory = SessionFactoryUtils.getSessionFactory();
    }
    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("SELECT c FROM Customer  c WHERE c.id = :id", Customer.class)
                .setParameter("id", id)
                .getSingleResult();
        session.getTransaction().commit();

    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();

    }

    @Override
    public Customer findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.getTransaction().commit();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> customers = session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        session.getTransaction().commit();
        return customers;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
