package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Application;
import com.growthgenius.loan.dto.ApplicationDto;
import com.growthgenius.loan.exception.BaseException;
import com.growthgenius.loan.exception.ResultType;
import com.growthgenius.loan.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ApplicationDto.Response get(Long applicationId) {
        Application application =
            applicationRepository.findById(applicationId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        return application.mapToResponse();
    }

    @Override
    @Transactional
    public ApplicationDto.Response update(Long applicationId, ApplicationDto.Request request) {
        Application application =
            applicationRepository.findById(applicationId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        application.update(request);
        return application.mapToResponse();
    }

    @Override
    @Transactional
    public void delete(Long applicationId) {
        Application application =
            applicationRepository.findById(applicationId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        application.delete();
    }

}
