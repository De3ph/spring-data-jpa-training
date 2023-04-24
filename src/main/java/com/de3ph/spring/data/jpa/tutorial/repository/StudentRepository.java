package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String substring);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    boolean existsByFirstName(String firstName);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL query
    @Query("SELECT s FROM Student s where s.emailId = ?1 ")
    Student getStudentByEmailAddress(String emailId);

    @Query("SELECT s.firstName FROM Student s where s.emailId = ?1 ")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native Query : jpql yerine sql dilinde query çalıştırtıyor

    @Query(value = "SELECT * FROM tbl_student s where s.email_address = ?1 ", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM student_sequence"
    )
    String getStudentSequenceFromDB();
}
