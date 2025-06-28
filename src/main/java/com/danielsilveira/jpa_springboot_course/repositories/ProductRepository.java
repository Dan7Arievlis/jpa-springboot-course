package com.danielsilveira.jpa_springboot_course.repositories;

import com.danielsilveira.jpa_springboot_course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
