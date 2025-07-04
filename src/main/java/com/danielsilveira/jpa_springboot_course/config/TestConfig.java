package com.danielsilveira.jpa_springboot_course.config;

import com.danielsilveira.jpa_springboot_course.entities.*;
import com.danielsilveira.jpa_springboot_course.entities.enums.OrderStatus;
import com.danielsilveira.jpa_springboot_course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category(null, "Electronics");
        Category category2 = new Category(null, "Books");
        Category category3 = new Category(null, "Computers");

        Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(List.of(category1, category2, category3));
        productRepository.saveAll(List.of(product1, product2, product3, product4, product5));

        product1.addCategory(category2);
        product2.addCategory(category1);
        product2.addCategory(category3);
        product3.addCategory(category3);
        product4.addCategory(category3);
        product5.addCategory(category2);

        productRepository.saveAll(List.of(product1, product2, product3, product4, product5));

        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "12345678");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "12345678");

        Order order1 = new Order(null, Instant.parse("2025-05-20T19:53:07Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2024-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
        Order order3 = new Order(null, Instant.parse("2025-02-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);

        userRepository.saveAll(List.of(user1, user2));
        orderRepository.saveAll(List.of(order1, order2, order3));

        OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
        OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
        OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());

        orderItemRepository.saveAll(List.of(orderItem1, orderItem2, orderItem3, orderItem4));

        Payment payment1 = new Payment(null, Instant.parse("2025-05-20T21:53:07Z"), order1);
        order1.setPayment(payment1);

        orderRepository.save(order1);
    }
}
