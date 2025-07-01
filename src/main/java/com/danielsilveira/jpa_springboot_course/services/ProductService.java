package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.Category;
import com.danielsilveira.jpa_springboot_course.entities.Product;
import com.danielsilveira.jpa_springboot_course.repositories.ProductRepository;
import com.danielsilveira.jpa_springboot_course.services.exceptions.DatabaseException;
import com.danielsilveira.jpa_springboot_course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insert(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product product) {
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, product);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Product addCategory(Long id, Category category) {
        try {
            Product product = repository.getReferenceById(id);
            product.addCategory(category);
            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new ResourceNotFoundException(category.getId());
        }
    }

    public Product removeCategory(Long id, Category category) {
        try {
            Product product = repository.getReferenceById(id);
            product.removeCategory(category);
            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImageUrl(product.getImageUrl());
    }
}
