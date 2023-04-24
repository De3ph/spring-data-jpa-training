package com.de3ph.spring.data.jpa.tutorial.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
/*
    Entity yerine embeddable kullandık, bunun nedeni db de yeni bir tablo yaratmak istemediğimizden
    guardian bilgilerini student entity sinde tutmak doğru değildi, fakat yeni bir tablo oluşturmak için bir gereksinim yoktu o yüzden:

    Guardian sınıfını @Embeddable ile anote ettik,
    Student sınıfında ise;
        @Embedded
        Guardian guardian; yaptık
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
// attribute override, database de attribute ların hangi isimde vereceğimizi değiştirmemize yarıyor
@AttributeOverride(name = "name", column = @Column(name = "guardian_name"))
@AttributeOverride(name = "email", column = @Column(name = "guardian_email"))
@AttributeOverride(name = "mobile", column = @Column(name = "guardian_mobile"))
@Builder
public class Guardian {
    private String name;
    private String email;
    private String mobile;
}
