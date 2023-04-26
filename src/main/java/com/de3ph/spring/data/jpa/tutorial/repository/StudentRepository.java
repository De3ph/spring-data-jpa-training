package com.de3ph.spring.data.jpa.tutorial.repository;

import com.de3ph.spring.data.jpa.tutorial.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    /*
    * eğer üstteki metodu jpql ile yazmak isteseydik şöyle yapardık
    *
    * @Query("SELECT s.firstName, s.lastName FROM Student s WHERE s.firstName = ?1 and s.lastName = ?2 ")
    *
    * */

    //JPQL query
    @Query("SELECT s FROM Student s where s.emailId = ?1 ")
    Student getStudentByEmailAddress(String emailId);

    @Query("SELECT s.firstName FROM Student s where s.emailId = :emailId ")
    String getStudentFirstNameByEmailAddress(@Param("emailId") String emailId);
    /*
    * @Param sayesinde parametreleri isimlendirip sorguda isim ile kullanabiliyoruz
    *
    * @Param("abc") -> sorguda da = :abc
    * */

    //Native Query : jpql yerine sql dilinde query çalıştırtıyor

    @Query(value = "SELECT * FROM tbl_student s where s.email_address = ?1 ", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    /*
    * NativeQuery i resource -> META-INF -> jpa-named-queries.properties dosyasında yazdık
    * Tek yapmamız gereken @Query ile native query olduğunu belirtmek
    *
    *  ! .properties deki isim ile metot ismi eşleşmeli !
    *
    * */
    @Query(nativeQuery = true)
    String getStudentSequenceFromDB();

    @Modifying // database deki verileri modify etmesi için bi nevi izin vermiş oluyoruz bu notasyon ile
    @Transactional // update ve delete querylerinde eklemek zorunlu, en başta repo interface ini de anote edebilirdik
    @Query(
            value = "UPDATE tbl_student SET first_name = :first_name WHERE email_address= :emailId",
            nativeQuery = true
    )
    // metodun dönüş tipinin int olmasının sebebi database i modify ettiğinden kaç record etkilendi onun sayısını dönmesi
    int updateStudentNameByEmailId(@Param("first_name") String firstName, @Param("emailId") String emailId);
}
