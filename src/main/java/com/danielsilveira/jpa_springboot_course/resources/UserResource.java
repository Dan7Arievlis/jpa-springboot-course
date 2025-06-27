package com.danielsilveira.jpa_springboot_course.resources;

import com.danielsilveira.jpa_springboot_course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> findAll() {
        User user = new User(1L, "Daniel", "daniel@gmail.com", "999999999", "1234567");

        return ResponseEntity.ok().body(user);
    }
}
