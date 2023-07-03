package org.zerock.j1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_todo2")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 항상 Long 타입을 쓴다
    private Long tno;

    @Column(length = 300, nullable = false)
    private String title;

    



}
