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
    @DisplayName("")
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

}