package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Application;
import com.growthgenius.loan.dto.ApplicationDto;
import com.growthgenius.loan.repository.ApplicationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Test
    @DisplayName("대출 신청")
    void createApplication() {
        // given
        Application application = Application.builder().name("seunggu")
            .cellPhone("010-1234-1234")
            .email("email@email.com")
            .hopeAmount(BigDecimal.valueOf(500000000))
            .build();
        ApplicationDto.Request request = ApplicationDto.Request.builder()
            .name("seunggu")
            .cellPhone("010-1234-1234")
            .email("email@email.com")
            .hopeAmount(BigDecimal.valueOf(500000000))
            .build();
        when(applicationRepository.save(any(Application.class))).thenReturn(application);
        // when
        ApplicationDto.Response response = applicationService.create(request);
        // then
        assertThat(response.getHopeAmount()).isEqualTo(application.getHopeAmount());
        assertThat(response.getName()).isEqualTo(application.getName());

    }

    @Test
    @DisplayName("존재하는 대출 신청 조회")
    void findByApplicationId() {
        // given
        Long findId = 1L;

        Application application = Application.builder().id(findId).build();
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(application));
        // when
        ApplicationDto.Response response = applicationService.get(findId);
        // then
        assertThat(response.getApplicationId()).isEqualTo(findId);
    }

}