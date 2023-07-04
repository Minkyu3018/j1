package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Entity 클래스 => 객체를 만들어냄 : Entity 객체로 부름
@Entity
@Table(name="tbl_sample")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Sample {

    @Id
    private String keyCol;
    
    private String first;

    private String last;

    private String zipCode;

    private String city;
}
