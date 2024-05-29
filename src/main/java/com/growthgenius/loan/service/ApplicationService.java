package com.growthgenius.loan.service;

import com.growthgenius.loan.dto.ApplicationDto.Request;
import com.growthgenius.loan.dto.ApplicationDto.Response;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);

    Response update(Long applicationId, Request request);

}
