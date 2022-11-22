package com.geekbrains.repository;

import com.geekbrains.model.Order;
import com.geekbrains.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrderDAO implements CRUDRepository<Order, Long> {

    private final SessionFactory sessionFactory;

    public OrderDAO() {
        sessionFactory = SessionFactoryUtils.getSessionFactory();
    }
    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order order = session.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
        session.remove(order);
        session.getTransaction().commit();
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(order);
        session.getTransaction().commit();
    }

    @Override
    public Order findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Order orderFromDb = session.get(Order.class, id);
        session.getTransaction().commit();
        return orderFromDb;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        orders = session.createQuery("SELECT o FROM Order o", Order.class).getResultList();
        session.getTransaction().commit();
        return orders;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
