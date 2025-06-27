package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Category;
import com.danielsilveira.jpa_springboot_course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Category findById(Long id) {
        return repository.findById(id).get();
    }
}
