package ru.inv1ct0;

import org.hibernate.cfg.Configuration;
import ru.inv1ct0.persist.Customer;
import ru.inv1ct0.persist.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class CustomerPurchases {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        List<Product> products = new ArrayList<>();
        products.add(new Product("Lemon",400));
        products.add(new Product("Apple",150));
        products.add(new Product("Bread",50));
        Customer customer = new Customer("Alex",products);
        Customer customer1 = new Customer("Bob", products);
        em.getTransaction().begin();

        try {
            em.persist(customer);
            em.persist(customer1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        System.out.println(customer);

        System.out.println("-----------------------------------------------------------------");
        List<Customer> customers = em.createQuery("from Customer c join fetch c.products p where c.name='Alex'", Customer.class).getResultList();
        customers.forEach(System.out::println);
        em.createQuery("from Customer c join fetch c.products p where p.productName='Lemon'", Customer.class).getResultList();
        customers.forEach(System.out::println);


        // Попытка удаления таблицы
//        Customer customer2 = em.find(Customer.class, 2L);
//        System.out.println("CUSTOMER INFO");
//        System.out.println("-----------------------");
//        System.out.println(customer2);
//        em.remove(customer2);
//        System.out.println("-----------------------");

        em.close();
    }
}
