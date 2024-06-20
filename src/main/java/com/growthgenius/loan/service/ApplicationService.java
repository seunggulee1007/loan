package com.growthgenius.loan.service;

import com.growthgenius.loan.dto.ApplicationDto;

public interface ApplicationService {

    ApplicationDto.Response create(ApplicationDto.Request request);

}
