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
    @Column(length = 50)
    private String address;

    /**
     * 상세 주소
     */
    @Column(length = 50)
    private String addressDetail;

    /**
     * 신청일자
     */
    private LocalDateTime appliedAt;

    /**
     * 전화번호
     */
    @Column(length = 13)
    private String cellPhone;

    /**
     * 책임자 이메일
     */
    @Column(length = 50)
    private String email;

    /**
     * 상담메모
     */
    private String memo;

    /**
     * 상담 요청자
     */
    @Column(length = 12)
    private String name;

    /**
     * 우편번호
     */
    private String zipCode;

}
