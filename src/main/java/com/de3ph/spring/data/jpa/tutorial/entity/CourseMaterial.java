package com.de3ph.spring.data.jpa.tutorial.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    /*
    * CASCADE : sql de parent ve child tabloların aynı anda delete ve update işlemlerinde nasıl bir yol izleneceğini belirtiyor
    *
    * FETCH : iki tablo arasında ilişki olunca, bir tabloya select atınca ilişkisi bulunan tablodaki bilgilerin de fetch edilip edilmeyeceğini belirtir
    *   Lazy seçilirse getirmez, Eager da getirir
    *
    * */
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY

    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
