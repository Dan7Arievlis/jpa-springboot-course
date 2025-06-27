package com.danielsilveira.jpa_springboot_course.repositories;

import com.danielsilveira.jpa_springboot_course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
