package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Category;
import com.danielsilveira.jpa_springboot_course.repositories.CategoryRepository;
import com.danielsilveira.jpa_springboot_course.services.exceptions.DatabaseException;
import com.danielsilveira.jpa_springboot_course.services.exceptions.DuplicateCategoryException;
import com.danielsilveira.jpa_springboot_course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Category insert(Category category) {
        if (!repository.findAll().contains(category))
            return repository.save(category);
        throw new DuplicateCategoryException(category);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
