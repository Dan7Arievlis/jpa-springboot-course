package com.danielsilveira.jpa_springboot_course.services.exceptions;

import com.danielsilveira.jpa_springboot_course.entities.Category;

import java.io.Serial;

public class DuplicateCategoryException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateCategoryException(Category category) {
        super("Category " + category.getName() + " already exists.");
    }
}
