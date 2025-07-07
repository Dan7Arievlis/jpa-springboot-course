package com.danielsilveira.jpa_springboot_course.repositories;

import com.danielsilveira.jpa_springboot_course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
