package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Application;
import com.growthgenius.loan.dto.ApplicationDto;
import com.growthgenius.loan.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationDto.Response create(ApplicationDto.Request request) {
        Application application = Application.from(request);
        applicationRepository.save(application);
        return application.mapToResponse();
    }

}
