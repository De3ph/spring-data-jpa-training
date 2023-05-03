package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Course;
import com.de3ph.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.de3ph.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void printCourses(){
        List<Course> courses = repository.findAll();
        System.out.println("courses: "+courses);
    }

    @Test
    void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Priyanka")
                .lastName("Singh")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
    }
}