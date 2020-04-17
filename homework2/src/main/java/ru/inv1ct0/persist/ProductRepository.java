package ru.inv1ct0.persist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {

    private final AtomicInteger identity = new AtomicInteger(0);

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        insert(new Product("Lemon", "400"));
        insert(new Product("Flour", "50"));
        insert(new Product("Bread", "25"));
    }

    public void insert(Product product) {
        product.setId(identity.incrementAndGet());
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> findUsingIterator(String title) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getTitle().equals(title)) {
                return products;
            }
        }
        return null;
    }
}
