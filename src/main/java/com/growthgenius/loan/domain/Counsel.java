package com.growthgenius.loan.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "counsel")
public class Counsel extends BaseEntity {

    @Id
    @Column(name = "counsel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주소
     */
    @Column(length = 50, columnDefinition = "varchar(50) COMMENT '주소'")
    private String address;

    /**
     * 상세 주소
     */
    @Column(length = 50, columnDefinition = "varchar(50) COMMENT '상세 주소'")
    private String addressDetail;

    /**
     * 신청일자
     */
    @Column(nullable = false, columnDefinition = "datetime COMMENT '신청일자'")
    private LocalDateTime appliedAt;

    /**
     * 전화번호
     */
    @Column(length = 13, nullable = false, columnDefinition = "varchar(13) comment '전화번호'")
    private String cellPhone;

    /**
     * 책임자 이메일
     */
    @Column(length = 50, columnDefinition = "varchar(50) COMMENT '상담 요청자 이메일'")
    private String email;

    /**
     * 상담메모
     */
    @Column(columnDefinition = "text COMMENT '상담메모'")
    private String memo;

    /**
     * 상담 요청자
     */
    @Column(length = 12, columnDefinition = "varchar(12) COMMENT '상담 요청자'")
    private String name;

    /**
     * 우편번호
     */
    @Column(length = 5, columnDefinition = "varchar(5) COMMENT '우편번호'")
    private String zipCode;

}
