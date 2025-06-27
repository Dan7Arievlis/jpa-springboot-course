package com.danielsilveira.jpa_springboot_course.config;

import com.danielsilveira.jpa_springboot_course.entities.User;
import com.danielsilveira.jpa_springboot_course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "12345678");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "12345678");

        userRepository.saveAll(List.of(user1, user2));
    }
}
