package com.danielsilveira.jpa_springboot_course.repositories;

import com.danielsilveira.jpa_springboot_course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
