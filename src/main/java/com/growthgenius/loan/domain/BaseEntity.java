package com.growthgenius.loan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    /**
     * 생성일자
     */
    @CreatedDate
    @Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP NOT NULL COMMENT '생성일자'")
    protected LocalDateTime createdAt;

    /**
     * 이용가능여부
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '수정일자'")
    protected LocalDateTime updatedAt;

    /**
     * 이용 가능 여부
     */
    @Column(name = "is_deleted")
    protected boolean deleted;

}
