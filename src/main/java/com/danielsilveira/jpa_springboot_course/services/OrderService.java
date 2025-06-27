package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Order;
import com.danielsilveira.jpa_springboot_course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Order findById(Long id) {
        return repository.findById(id).get();
    }
}
