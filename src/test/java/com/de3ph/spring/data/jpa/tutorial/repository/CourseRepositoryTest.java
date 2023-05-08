package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Course;
import com.de3ph.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.de3ph.spring.data.jpa.tutorial.entity.Student;
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

    @Test
    void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Lizzie")
                .lastName("Morgan")
                .build();
        Student student = Student.builder()
                .firstName("Abishek")
                .lastName("Singh")
                .emailId("abishek@gmail.com")
                .build();
        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build()
                ;

        course.addStudents(student);

        repository.save(course);
    }
}