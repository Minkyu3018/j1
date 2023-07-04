package org.zerock.j1.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass // 테이블로 만들어지지 말아라
@Getter
@EntityListeners(value = { AuditingEntityListener.class })
public abstract class BaseEntity {
    
    @CreatedDate // 생성 날짜
    @Column(updatable = false) // 업데이트 시 수정하지 말아라
    private LocalDateTime regDate;

    @LastModifiedDate // 마지막 수정 날짜
    private LocalDateTime modDate;

}
