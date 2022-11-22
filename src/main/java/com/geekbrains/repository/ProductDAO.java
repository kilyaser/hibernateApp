package com.geekbrains.repository;

import com.geekbrains.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class ProductDAO implements CRUDRepository<Product, Long> {
    private final SessionFactory sessionFactory;


    public ProductDAO() {
        sessionFactory = SessionFactoryUtils.getSessionFactory();
    }
    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }
    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//        Product product = session.get(Product.class, id);
        Product product = session.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        session.remove(product);
        session.getTransaction().commit();
    }
    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
    }
    @Override
    public Product findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product productFromDb = session.get(Product.class, id);
        session.getTransaction().commit();
        return productFromDb;
    }
    @Override
    public List<Product> findAll() {
        List<Product> products;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        products = session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        session.getTransaction().commit();
        return products;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }



}
