package com.growthgenius.loan.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.growthgenius.loan.dto.ApplicationDto.Request;
import static com.growthgenius.loan.dto.ApplicationDto.Response;
import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Entity
@Builder
@Table(name = "application")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Application extends BaseEntity {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(12) default null comment '신청자'")
    private String name;

    @Column(columnDefinition = "varchar(13) default null comment '전화번호'")
    private String cellPhone;

    @Column(columnDefinition = "varchar(50) default null comment '신청자 이메일'")
    private String email;

    @Column(columnDefinition = "decimal(5,4) default null comment '금리'")
    private BigDecimal interestRate;

    @Column(columnDefinition = "decimal(5,4) default null comment '신청수수료'")
    private BigDecimal fee;

    @Column(columnDefinition = "datetime default null comment '만기'")
    private LocalDateTime maturity;

    @Column(columnDefinition = "decimal(15,2) default null comment '희망 금액'")
    private BigDecimal hopeAmount;

    @Column(columnDefinition = "datetime default null comment '신청일자'")
    private LocalDateTime appliedAt;

    public static Application from(Request request) {
        Application application = new Application();
        application.name = request.getName();
        application.email = request.getEmail();
        application.cellPhone = request.getCellPhone();
        application.hopeAmount = request.getHopeAmount();
        application.appliedAt = LocalDateTime.now();
        return application;
    }

    public Response mapToResponse() {
        Response response = new Response();
        copyProperties(this, response);
        response.setApplicationId(this.id);
        return response;
    }

    public void update(Request request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.cellPhone = request.getCellPhone();
        this.hopeAmount = request.getHopeAmount();
    }

}
