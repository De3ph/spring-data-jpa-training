package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Course;
import com.de3ph.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    void saveTeacher(){
        Course course = Course.builder()
                .title("DBA")
                .credit(5)
                .build();
        Course course2 = Course.builder()
                .title("JAVA")
                .credit(6)
                .build();
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course2);
        Teacher teacher = Teacher.builder()
                .firstName("Qutub")
                .lastName("Khan")
//                .courses(courses)
                .build();

        repository.save(teacher);
    }


}