package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Guardian;
import com.de3ph.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
/*

    Normal şartlarda repository ler test edilirken
    @DataJpaTest notasyonu kullanılır, böylece database bu test verilerinden etkilenmez

 */
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveStudent() {
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
                .build();

        studentRepository.save(student);
    }

    @Test
    void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder().email("nikhil@gmail.com").name("nikhil").mobile("999999992").build();
        Student student = Student.builder()
                .emailId("shivam@gmail.com")
                .firstName("Shivam")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("student list: " + studentList);
    }

    @Test
    void printStudentByFirstName() {
        List<Student> studentList = studentRepository.findByFirstName("Shivam");
        System.out.println("student List : " + studentList);
    }
    @Test
    void printStudentByFirstNameContaining() {
        List<Student> studentList = studentRepository.findByFirstNameContaining("Shiv");
        System.out.println("student List : " + studentList);
    }
    @Test
    void printStudentByLastNameNotNull() {
        List<Student> studentList = studentRepository.findByLastNameNotNull();
        System.out.println("student List : " + studentList);
    }
    @Test
    void printStudentByGuardianName() {
        List<Student> studentList = studentRepository.findByGuardianName("nikhil");
        System.out.println("student List : " + studentList);
    }
    @Test
    void printExistByFirstName() {
        String validStudentName = "Shivam";
        boolean isStudentExist = studentRepository.existsByFirstName(validStudentName);
        System.out.println("Is student named with "+validStudentName+" exist? : "+isStudentExist);

        String inValidStudentName = "Messi";
        boolean isStudentExist2 = studentRepository.existsByFirstName(inValidStudentName);
        System.out.println("Is student named with "+inValidStudentName+" exist? : "+isStudentExist2);
    }

    @Test
    void printStudentByFirstNameAndLastName() {
        List<Student> studentList = studentRepository.findByFirstNameAndLastName("Shivam","Kumar");
        System.out.println("student List : " + studentList);
    }

    @Test
    void printGetStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("shabbir@gmail.com");
        System.out.println("student  : " + student);
    }

    @Test
    void printGetStudentFirstNameByEmailAddress() {
        String studentName = studentRepository.getStudentFirstNameByEmailAddress("shabbir@gmail.com");
        System.out.println("student name : " + studentName);
    }

    @Test
    void printGetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("shabbir@gmail.com");
        System.out.println("student : " + student);
    }

    @Test
    void printGetStudentSequenceNative() {
        String sequence = studentRepository.getStudentSequenceFromDB();
        System.out.println("sequence : " + sequence);
    }
}