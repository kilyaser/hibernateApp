package com.geekbrains;

import com.geekbrains.model.Product;
import com.geekbrains.repository.ProductDAO;

import java.util.List;

public class HibernateApp {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        //===save product===
        productDAO.save(new Product("Milk", 10));
        productDAO.save(new Product("Bread", 5));
        productDAO.save(new Product("Apple", 2));
        productDAO.save(new Product("Banana", 5));
        productDAO.save(new Product("Cooke", 7));

        //===find product by Id==
        Product productDb = productDAO.findById(1L);
        System.out.println(productDb);

        //===findAll===
        List<Product> products = productDAO.findAll();
        System.out.println(products);

        //===update===
//        Product productDb2 = productDAO.findById(1L);
//        productDb2.setTitle("White Bread");
//        productDAO.update(productDb2);

        //====Delete===
//        productDAO.deleteById(1L);


        productDAO.close();
    }
}
