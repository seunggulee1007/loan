package com.growthgenius.loan.repository;

import com.growthgenius.loan.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
