package com.danielsilveira.jpa_springboot_course.resources;

import com.danielsilveira.jpa_springboot_course.entities.Category;
import com.danielsilveira.jpa_springboot_course.entities.Product;
import com.danielsilveira.jpa_springboot_course.repositories.ProductRepository;
import com.danielsilveira.jpa_springboot_course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        product = service.insert(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product = service.update(id, product);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(value = "/c/{id}")
    public ResponseEntity<Product> addCategory(@PathVariable Long id, @RequestBody Category category) {
        Product product = service.addCategory(id, category);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping(value = "/r/{id}")
    public ResponseEntity<Product> removeCategory(@PathVariable Long id, @RequestBody Category category) {
        Product product = service.removeCategory(id, category);
        return ResponseEntity.ok().body(product);
    }
}
