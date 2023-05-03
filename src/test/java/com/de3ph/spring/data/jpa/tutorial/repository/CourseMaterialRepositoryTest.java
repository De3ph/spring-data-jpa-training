package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Course;
import com.de3ph.spring.data.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;

    @Test
    void saveCourseMaterial(){
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    void saveCourseMaterialWithoutCourse(){
        // fail olmalı
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.python.org")
                .build();

        repository.save(courseMaterial);

    }

    @Test
    void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = repository.findAll();
        System.out.println("all course materials: "+ courseMaterials);
    }

}