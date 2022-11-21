package com.geekbrains.repository;

import com.geekbrains.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ProductDAO {
    private SessionFactory sessionFactory;


    public ProductDAO() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
    }

    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
    }

    public Product findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product productFromDb = session.get(Product.class, id);
        session.getTransaction().commit();
        return productFromDb;
    }

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
