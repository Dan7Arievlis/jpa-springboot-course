package com.danielsilveira.jpa_springboot_course.config;

import com.danielsilveira.jpa_springboot_course.entities.Order;
import com.danielsilveira.jpa_springboot_course.entities.User;
import com.danielsilveira.jpa_springboot_course.repositories.OrderRepository;
import com.danielsilveira.jpa_springboot_course.repositories.UserRepository;
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

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "12345678");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "12345678");

        Order order1 = new Order(null, Instant.parse("2025-05-20T19:53:07Z"), user1);
        Order order2 = new Order(null, Instant.parse("2024-07-21T03:42:10Z"), user2);
        Order order3 = new Order(null, Instant.parse("2025-02-22T15:21:22Z"), user1);

        userRepository.saveAll(List.of(user1, user2));
        orderRepository.saveAll(List.of(order1, order2, order3));
    }
}
