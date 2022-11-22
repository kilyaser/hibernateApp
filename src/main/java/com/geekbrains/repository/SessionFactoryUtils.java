package com.geekbrains.repository;

import com.geekbrains.model.Customer;
import com.geekbrains.model.Order;
import com.geekbrains.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            return new Configuration()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Order.class)
                    .buildSessionFactory();
        } else {
            return sessionFactory;
        }

    }

}
