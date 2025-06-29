package com.danielsilveira.jpa_springboot_course.services;

import com.danielsilveira.jpa_springboot_course.entities.User;
import com.danielsilveira.jpa_springboot_course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public User findById(Long id) {
        return repository.findById(id).get();
    }

    public User insert(User user) {
        return repository.save(user);
    }
}
