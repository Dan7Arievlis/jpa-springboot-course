package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Product;
import com.danielsilveira.jpa_springboot_course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Product findById(Long id) {
        return repository.findById(id).get();
    }
}
