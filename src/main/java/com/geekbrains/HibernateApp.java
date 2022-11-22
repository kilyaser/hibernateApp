package com.geekbrains;

import com.geekbrains.repository.OrderDAO;
import com.geekbrains.repository.ProductDAO;


public class HibernateApp {

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();

        System.out.println(orderDAO.findById(1L));
        System.out.println(orderDAO.findAll());


        orderDAO.close();
    }
}
